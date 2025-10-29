package Servicio;

import Modelos.Entidades.DTOs.HechoDTOInput;
import Modelos.Entidades.*;
import Modelos.Entidades.DTOs.UbicacionDTOInput;
import Modelos.Entidades.DTOs.UbicacionDTOOutput;
import Modelos.Exceptions.ColeccionNoEncontradaException;
import Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime ;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgregadorServicio {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HechoRepositorio hechoRepositorio;
    @Autowired
    ColeccionRepositorio coleccionRepositorio;
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    @Autowired
    ProvinciaRepositorio provinciaRepositorio;
    @Autowired
    PaisRepositorio paisRepositorio;
    @Autowired
    LocalidadRepositorio localidadRepositorio;
    @Autowired
    UbicacionRepositorio ubicacionRepositorio;
    @Autowired
    ContribuyenteRepositorio contribuyenteRepositorio;
    @Autowired
    ContenidoRepositorio contenidoRepositorio;

    @Transactional
    public void actualizarHechos() {

        UriComponentsBuilder urlDinamica = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/dinamica/hechos"); // cambiar nombre url
        UriComponentsBuilder urlDemo = UriComponentsBuilder.fromHttpUrl("http://localhost:8086/demo/hechos");
        UriComponentsBuilder urlMetamapa = UriComponentsBuilder.fromHttpUrl("http://localhost:8086/metamapa/hechos");
        UriComponentsBuilder urlEstatica = UriComponentsBuilder.fromHttpUrl("http://localhost:8084/fuenteEstatica/hechos");

        ResponseEntity<List<HechoDTOInput>> respuestaDinamica = restTemplate.exchange(
                urlDinamica.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HechoDTOInput>>() {
                }
        );


        ResponseEntity<List<HechoDTOInput>> respuestaDemo = restTemplate.exchange(
               urlDemo.toUriString(),
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<>() {
               }
       );

       ResponseEntity<List<HechoDTOInput>> respuestaMetamapa = restTemplate.exchange(
               urlMetamapa.toUriString(),
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<>() {
               }
       );

       ResponseEntity<List<HechoDTOInput>> respuestaEstatica = restTemplate.exchange(
               urlEstatica.toUriString(),
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<>() {
               }
       );
        List<HechoDTOInput> hechosDTOTotales = new ArrayList<>();



        if (!respuestaDemo.getBody().isEmpty()) {
            List<HechoDTOInput> hechosDemo = this.setearOrigenCarga(respuestaDemo.getBody(), OrigenCarga.FUENTE_PROXY);
            hechosDTOTotales.addAll(hechosDemo) ;
        }

        if (!respuestaDinamica.getBody().isEmpty()) {
            List<HechoDTOInput> hechosDinamica = this.setearOrigenCarga(respuestaDinamica.getBody(), OrigenCarga.FUENTE_DINAMICA);
            hechosDTOTotales.addAll(hechosDinamica);
        }


       if (!respuestaEstatica.getBody().isEmpty()) {
           List<HechoDTOInput> hechosEstatica = this.setearOrigenCarga(respuestaEstatica.getBody(), OrigenCarga.FUENTE_ESTATICA);
           hechosDTOTotales.addAll(hechosEstatica) ;
       }

       if (!respuestaMetamapa.getBody().isEmpty()) {
           List<HechoDTOInput> hechosMetamapa = this.setearOrigenCarga(respuestaMetamapa.getBody(), OrigenCarga.FUENTE_PROXY);
           hechosDTOTotales.addAll(hechosMetamapa) ;
       }


        UriComponentsBuilder urlCategoria = UriComponentsBuilder.fromHttpUrl("http://localhost:8085/normalizacion/categorias");
        UriComponentsBuilder urlUbicacion = UriComponentsBuilder.fromHttpUrl("http://localhost:8085/normalizacion/ubicaciones");
        UriComponentsBuilder urlTitulo = UriComponentsBuilder.fromHttpUrl("http://localhost:8085/normalizacion/titulos");

        for (HechoDTOInput hechoDTO: hechosDTOTotales){

            //CATEEGORIAAAAAA

            String categoriaRequest = hechoDTO.getCategoria();
            System.out.printf("Categoria desnormalizada: %s", categoriaRequest);

            ResponseEntity<String> categoriaResponse = restTemplate.exchange(
                    urlCategoria.toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(categoriaRequest),
                    String.class
            );
            String categoriaNormalizada = categoriaResponse.getBody();
            System.out.printf("Categoria Normalizada: %s", categoriaNormalizada ) ;

            if (categoriaNormalizada != null) {
                hechoDTO.setCategoria(categoriaNormalizada); // solo guardamos el nombre
            }

            //UBICACIOOOOONN
            UbicacionDTOOutput ubicacionDTOOutput = new UbicacionDTOOutput(hechoDTO.getLatitud(), hechoDTO.getLongitud());

            ResponseEntity<UbicacionDTOInput> UbicacionNormalizada = restTemplate.exchange(
                    urlUbicacion.toUriString(), // URL de tu API
                    HttpMethod.POST,
                    new HttpEntity<>(ubicacionDTOOutput),
                    UbicacionDTOInput.class
            );

            hechoDTO.setPais(UbicacionNormalizada.getBody().getPais());
            hechoDTO.setProvincia(UbicacionNormalizada.getBody().getProvincia());
            hechoDTO.setLocalidad(UbicacionNormalizada.getBody().getLocalidad());

            //TITULOOO
            String tituloRequest = hechoDTO.getTitulo();
            ResponseEntity<String> tituloResponse = restTemplate.exchange(
                    urlTitulo.toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(tituloRequest),
                    String.class
            );
            String tituloNormalizado = tituloResponse.getBody();
            if (tituloNormalizado != null) {
                hechoDTO.setTitulo(tituloNormalizado);
            }
        }
        //if (!hechosDTOTotales.isEmpty()) {
            List<Hecho> hechos = this.transaformarAHecho(hechosDTOTotales);
            hechoRepositorio.saveAll(hechos);
            this.actualizarColecciones();
        //}
    }

    public List<Hecho> transaformarAHecho(List<HechoDTOInput> hechosDTO) {
        List<Hecho> hechos = new ArrayList<>();
        for (HechoDTOInput hechoDTO : hechosDTO) {
            Pais pais = this.crearPais(hechoDTO.getPais());
            Provincia provincia = this.crearProvincia(hechoDTO.getProvincia(), pais);
            Localidad localidad = this.crearLocalidad(hechoDTO.getLocalidad(), provincia);
            Categoria categoria = this.crearCategoria(hechoDTO.getCategoria());
            Ubicacion ubicacion = this.crearUbicacion(hechoDTO.getLatitud(), hechoDTO.getLongitud(), localidad, provincia, pais);
            Contribuyente contribuyente = this.crearContribuyente(hechoDTO.getUsuario(), hechoDTO.getNombre(), hechoDTO.getApellido(), hechoDTO.getFecha_nacimiento());
            Contenido contenido = this.crearContenido(hechoDTO.getContenido(), hechoDTO.getContenido_multimedia());
            Hecho hecho = new Hecho(
                    hechoDTO.getIdFuente(),
                    hechoDTO.getTitulo(),
                    hechoDTO.getDescripcion(),
                    contenido,
                    categoria,
                    hechoDTO.getFechaAcontecimiento(),
                    ubicacion,
                    LocalDateTime .now(),
                    OrigenCarga.valueOf(hechoDTO.getOrigen_carga().toUpperCase()),
                    true,
                    contribuyente,
                    hechoDTO.getAnonimo() // el ? : funciona como un if(?) y else(:)
            );

            hechos.add(hecho);

        }
        return hechos;
    }

    public  Contenido crearContenido( String texto, String contenidoMultimedia){
        Contenido contenido = contenidoRepositorio.findByTextoAndContenidoMultimedia(texto, contenidoMultimedia);
        if(contenido == null){
            contenido = new Contenido(texto, contenidoMultimedia);
            contenidoRepositorio.save(contenido);
        }
        return contenido;
    }

    public Contribuyente crearContribuyente(String usuario, String nombre, String apellido, LocalDateTime fechaNacimiento) {
        if(usuario == null){
            return null;
        }
        Contribuyente contribuyente = contribuyenteRepositorio.findByUsuario(usuario);
        if(contribuyente == null){
            contribuyente = new Contribuyente(usuario, nombre, apellido, fechaNacimiento);
            contribuyenteRepositorio.save(contribuyente);
        }
        return contribuyente;
    }

    public Categoria crearCategoria(String nombre) {
        Categoria categoria = categoriaRepositorio.findByNombre(nombre);
        if(categoria == null){
            categoria = new Categoria(nombre);
            categoriaRepositorio.save(categoria);
        }
        return categoria;
    }

    public Pais crearPais(String nombre) {
        Pais pais = paisRepositorio.findByPais(nombre);
        if(pais == null){
            pais = new Pais(nombre);
            paisRepositorio.save(pais);
        }
        return pais;
    }

    public Provincia crearProvincia(String nombre, Pais pais) {
        Provincia provincia = provinciaRepositorio.findByProvinciaAndPais(nombre, pais);
        if(provincia == null){
            provincia = new Provincia(nombre, pais);
            provinciaRepositorio.save(provincia);
        }
        return provincia;
    }

    public Localidad crearLocalidad(String nombre, Provincia provincia) {
        Localidad localidad = localidadRepositorio.findByLocalidadAndProvincia(nombre, provincia);
        if(localidad == null){
            localidad = new Localidad(nombre, provincia);
            localidadRepositorio.save(localidad);
        }
        return localidad;
    }

    public Ubicacion crearUbicacion(Double latitud, Double longitud, Localidad localidad, Provincia provincia, Pais pais) {
        Ubicacion ubicacion = ubicacionRepositorio.findByLatitudAndLongitud(latitud, longitud);
        if(ubicacion == null){
            ubicacion = new Ubicacion(localidad, provincia, pais, latitud, longitud);
            ubicacionRepositorio.save(ubicacion);
        }
        return ubicacion;
    }

    public void actualizarColecciones() {
        try {
            for (Coleccion coleccion : coleccionRepositorio.findAll()) {
                this.actualizarColeccion(coleccion);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarColeccion(Coleccion coleccion ) {

        List<Hecho> hechosCumplenCriterio = hechoRepositorio.filtrarHechos(
                Optional.ofNullable(coleccion.getCriterio_pertenencia().getCategoria())
                        .map(c->c.getNombre())
                        .orElse(null),
                coleccion.getCriterio_pertenencia().getMultimedia(),
                coleccion.getCriterio_pertenencia().getFechaCargaDesde(),
                coleccion.getCriterio_pertenencia().getFechaCargaHasta(),
                coleccion.getCriterio_pertenencia().getFechaAcontecimientoDesde(),
                coleccion.getCriterio_pertenencia().getFechaAcontecimientoHasta(),
                coleccion.getCriterio_pertenencia().getOrigen(),
                coleccion.getCriterio_pertenencia().getTitulo(),
                Optional.ofNullable(coleccion.getCriterio_pertenencia().getUbicacion())
                        .map(u -> u.getPais())
                        .map(p -> p.getPais())
                        .orElse(null),
                Optional.ofNullable(coleccion.getCriterio_pertenencia().getUbicacion())
                        .map(u -> u.getProvincia())
                        .map(p -> p.getProvincia())
                        .orElse(null),
                Optional.ofNullable(coleccion.getCriterio_pertenencia().getUbicacion())
                        .map(u -> u.getLocalidad())
                        .map(l -> l.getLocalidad())
                        .orElse(null)
        );
        if(coleccion.getHechos() != null){
            Set<Long> idsExistentes = coleccion.getHechos()
                    .stream()
                    .map(Hecho::getId)
                    .collect(Collectors.toSet());

        List<Hecho> nuevosHechos = hechosCumplenCriterio.stream()
                .filter(h -> !idsExistentes.contains(h.getId())&&h.getVisible())
                .toList();

        coleccion.agregarHechos(nuevosHechos);
        coleccionRepositorio.save(coleccion);
        }else{
            coleccion.agregarHechos(hechosCumplenCriterio);
            coleccionRepositorio.save(coleccion);
        }
    }


    public List<HechoDTOInput> setearOrigenCarga(List<HechoDTOInput> hechosDTO, OrigenCarga origenCarga) {
        for (HechoDTOInput hechoDTO : hechosDTO) {
            hechoDTO.setOrigen_carga(origenCarga.name());
        }

        return hechosDTO;
    }

    @Transactional
    public void cargarColeccionConHechos(Long coleccionId) throws ColeccionNoEncontradaException {

        Coleccion coleccion = coleccionRepositorio.findById(coleccionId).orElseThrow(ColeccionNoEncontradaException::new);
        actualizarColeccion(coleccion);
    }


}