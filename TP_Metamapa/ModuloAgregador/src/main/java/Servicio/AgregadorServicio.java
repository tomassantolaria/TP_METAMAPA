package Servicio;


import Modelos.Entidades.DTOs.CriteriosDTO;
import Modelos.Entidades.DTOs.FiltrarRequestDTO;
import Modelos.Entidades.DTOs.HechoDTOInput;
import Modelos.Entidades.*;
import Modelos.Entidades.DTOs.UbicacionDTO;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void actualizarHechos() {
        //Las URL tiene que tener este formato fromHttpUrl
        UriComponentsBuilder urlDinamica = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/dinamica/hechos"); // cambiar nombre url

        UriComponentsBuilder urlDemo = UriComponentsBuilder.fromPath("http://demo/hechos");

        UriComponentsBuilder urlEstatica = UriComponentsBuilder.fromPath("http://fuenteEstatica/hechos");

        UriComponentsBuilder urlMetamapa = UriComponentsBuilder.fromPath("http://metamapa/hechos");

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


        ResponseEntity<List<HechoDTOInput>> respuestaEstatica = restTemplate.exchange(
                urlEstatica.toUriString(),
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

        List<HechoDTOInput> hechosDTOTotales = new ArrayList<>();
        // Cambiar el HECHOdtoOutput de las distintas fuentes


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

        // CONSUMIR API DE GOOGLE PARA OBTENER UBICACION MEDIANTE LA LATITUD Y LONGITUD O QUE LO HAGA CADA FUENTE

        UriComponentsBuilder urlCategoria = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/normalizacion/categorias");
        UriComponentsBuilder urlUbicacion = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/normalizacion/ubicaciones");


        for ( HechoDTOInput hechoDTO: hechosDTOTotales){

            System.out.println("Pais antes de normalizar: " + hechoDTO.getNombre_pais());

            String request = hechoDTO.getCategoria();

            ResponseEntity<String> categoriaNormalizada = restTemplate.exchange(
                    urlCategoria.toUriString(), // URL de tu API
                    HttpMethod.POST,
                    new HttpEntity<>(request),
                    new ParameterizedTypeReference<>() {}
            );

            hechoDTO.setCategoria(categoriaNormalizada.getBody());

            UbicacionDTO ubicacionDTO = new UbicacionDTO(hechoDTO.getNombre_localidad(), hechoDTO.getNombre_provincia(), hechoDTO.getNombre_pais());

            ResponseEntity<UbicacionDTO> UbicacionNormalizada = restTemplate.exchange(
                    urlUbicacion.toUriString(), // URL de tu API
                    HttpMethod.POST,
                    new HttpEntity<>(ubicacionDTO),
                    UbicacionDTO.class
            );
            hechoDTO.setNombre_pais(UbicacionNormalizada.getBody().getPais());
            hechoDTO.setNombre_provincia(UbicacionNormalizada.getBody().getProvincia());
            hechoDTO.setNombre_localidad(UbicacionNormalizada.getBody().getLocalidad());

        }

        List<Hecho> hechos = this.transaformarAHecho(hechosDTOTotales);
        hechoRepositorio.saveAll(hechos); // los guarda en la BD asignandoles un ID
        //this.actualizarColecciones();

    }

    public List<Hecho> transaformarAHecho(List<HechoDTOInput> hechosDTO) {
        List<Hecho> hechos = new ArrayList<>();
        for (HechoDTOInput hechoDTO : hechosDTO) {
            Pais pais = new Pais(hechoDTO.getNombre_pais());
            Provincia provincia = new Provincia(hechoDTO.getNombre_provincia(), pais);
            Localidad localidad = new Localidad(hechoDTO.getNombre_localidad(), provincia);
            Hecho hecho = new Hecho(
                    hechoDTO.getIdHecho(),
                    hechoDTO.getIdFuente(),
                    hechoDTO.getTitulo(),
                    hechoDTO.getDescripcion(),
                    new Contenido(hechoDTO.getContenido(), hechoDTO.getContenido_multimedia()),
                    new Categoria(hechoDTO.getCategoria()),
                    hechoDTO.getFechaAcontecimiento(),
                    new Ubicacion(localidad, provincia, pais, hechoDTO.getLatitud(), hechoDTO.getLongitud()),
                    (hechoDTO.getFechaCarga() != null ? hechoDTO.getFechaCarga() : LocalDate.now()),
                    OrigenCarga.valueOf(hechoDTO.getOrigen_carga().toUpperCase()),
                    (hechoDTO.getVisible() != null ? hechoDTO.getVisible() : true),
                    (hechoDTO.getUsuario() != null ? new Contribuyente(hechoDTO.getUsuario(), hechoDTO.getNombre(), hechoDTO.getApellido(), hechoDTO.getFecha_nacimiento()) : null),
                    hechoDTO.getAnonimo() // el ? : funciona como un if(?) y else(:)
            );
            hechos.add(hecho);
        }
        return hechos;
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
                coleccion.getCriterio_pertenencia().getCategoria().getNombre(),
                coleccion.getCriterio_pertenencia().getMultimedia(),
                coleccion.getCriterio_pertenencia().getFecha_carga_desde(),
                coleccion.getCriterio_pertenencia().getFecha_carga_hasta(),
                coleccion.getCriterio_pertenencia().getFecha_acontecimiento_desde(),
                coleccion.getCriterio_pertenencia().getFecha_acontecimiento_hasta(),
                coleccion.getCriterio_pertenencia().getOrigen_carga().toString(),
                coleccion.getCriterio_pertenencia().getTitulo(),
                (coleccion.getCriterio_pertenencia().getUbicacion().getLocalidad().getNombre_localidad()),
                (coleccion.getCriterio_pertenencia().getUbicacion().getProvincia().getNombre_provincia()),
                (coleccion.getCriterio_pertenencia().getUbicacion().getPais().getNombre_pais())
        );

        Set<Long> idsExistentes = coleccion.getHechos()
                .stream()
                .map(Hecho::getId)
                .collect(Collectors.toSet());

        List<Hecho> nuevosHechos = hechosCumplenCriterio.stream()
                .filter(h -> !idsExistentes.contains(h.getId()))
                .toList();

        coleccion.agregarHechos(nuevosHechos);
        coleccionRepositorio.save(coleccion);
    }


    public List<HechoDTOInput> setearOrigenCarga(List<HechoDTOInput> hechosDTO, OrigenCarga origenCarga) {
        for (HechoDTOInput hechoDTO : hechosDTO) {
            hechoDTO.setOrigen_carga(origenCarga.name());
        }

        return hechosDTO;
    }

    public void cargarColeccionConHechos(Long coleccionId) {

        Coleccion coleccion = coleccionRepositorio.findById(coleccionId).orElseThrow();
        actualizarColeccion(coleccion);
    }
}