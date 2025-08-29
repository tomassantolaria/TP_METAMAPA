package Servicio;


import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.FiltrarRequestDTO;
import Modelos.DTOs.HechoDTOInput;
import Modelos.Entidades.*;
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
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgregadorServicio {

    @Autowired
    RestTemplate restTemplate;
    HechoRepositorio hechoRepositorio;
    ColeccionRepositorio coleccionRepositorio;

    public void actualizarHechos() {

        UriComponentsBuilder urlDinamica = UriComponentsBuilder.fromPath("http://dinamica/hechos/obtener"); // cambiar nombre url

        UriComponentsBuilder urlDemo = UriComponentsBuilder.fromPath("http://demo/hechos");

        UriComponentsBuilder urlEstatica = UriComponentsBuilder.fromPath("http://fuenteEstatica/hechos");

        UriComponentsBuilder urlMetamapa = UriComponentsBuilder.fromPath("http://metamapa/hechos");

        ResponseEntity<List<HechoDTOInput>> respuestaDinamica = restTemplate.exchange(
                urlDinamica.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
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
        // antes de transformar a hecho, filtrar los que ya existen en la base de datos con el normalizador

        List<Hecho> hechos = this.transaformarAHecho(hechosDTOTotales);
        this.guardarHechos(hechos); // los guarda en la BD asignandoles un ID
        this.actualizarColecciones(hechos);

    }

    public List<Hecho> transaformarAHecho(List<HechoDTOInput> hechosDTO) {
        List<Hecho> hechos = new ArrayList<>();
        for (HechoDTOInput hechoDTO : hechosDTO) {
            Hecho hecho = new Hecho(
                    hechoDTO.getIdHecho(),
                    hechoDTO.getIdFuente(),
                    hechoDTO.getTitulo(),
                    hechoDTO.getDescripcion(),
                    new Contenido(hechoDTO.getContenido(), hechoDTO.getContenido_multimedia()),
                    new Categoria(hechoDTO.getCategoria()),
                    hechoDTO.getFechaAcontecimiento(),
                    new Ubicacion(hechoDTO.getLugar(), hechoDTO.getLatitud(), hechoDTO.getLongitud()),
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

    public void guardarHechos(List<Hecho> hechos) {
        for (Hecho hecho : hechos) {
            hechoRepositorio.agregarHecho(hecho);
        }
    }

    public void actualizarColecciones(List<Hecho> hechos) {

            List<HechoDTOInput> hechosDTO = this.transformarADTOLista(hechos);

       for (Coleccion coleccion : coleccionRepositorio.getTodas()){

           CriteriosDTO criteriosDTO = this.transformarCriteriosADTO(coleccion.getCriterio_pertenencia());
           RestTemplate restTemplate = new RestTemplate();
           FiltrarRequestDTO request = new FiltrarRequestDTO(criteriosDTO, hechosDTO);

           ResponseEntity<List<HechoDTOInput>> response = restTemplate.exchange(
                   "http://localhost:8080/filtrar", // URL de tu API
                   HttpMethod.POST,
                   new HttpEntity<>(request),
                   new ParameterizedTypeReference<>() {}
           );

           List<Hecho> hechosRespuesta = this.transaformarAHecho(response.getBody());
           agregarHechosAColeccion(hechosRespuesta, coleccion);
       }
    }

    public List<HechoDTOInput> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTOInput> hechosDTO;
        hechosDTO = hechos.stream()
                .map(this::transformarAHechoDTO)
                .collect(java.util.stream.Collectors.toList());
        return hechosDTO;
    }
    
    public HechoDTOInput transformarAHechoDTO (Hecho hecho){

        return new HechoDTOInput(hecho.getId(), hecho.getIdFuente(), hecho.getTitulo(),hecho.getDescripcion(),
                                 hecho.getContenido().getTexto(),hecho.getContenido().getContenido_multimedia(),
                                 hecho.getCategoria().getNombre(), hecho.getFecha(),hecho.getFecha_carga(),
                                 hecho.getUbicacion().getNombre(), hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud(),
                                 (hecho.getContribuyente() != null ? hecho.getContribuyente().getUsuario() : null), (hecho.getContribuyente() != null ? hecho.getContribuyente().getNombre() : null),
                                 (hecho.getContribuyente() != null ? hecho.getContribuyente().getApellido() : null), (hecho.getContribuyente() != null ? hecho.getContribuyente().getFecha_nacimiento() : null),
                                 hecho.isAnonimo(),hecho.isVisible(), hecho.getOrigen_carga().name());
    }

    public CriteriosDTO transformarCriteriosADTO(CriteriosDePertenencia criterios) {
        String categoria = (criterios.getCategoria() != null) ? criterios.getCategoria().getNombre() : null;
        String multimedia = (criterios.getMultimedia() != null) ? criterios.getMultimedia().toString() : null;
        String fecha_carga_desde = (criterios.getFecha_carga_desde() != null) ? criterios.getFecha_carga_desde().toString() : null;
        String fecha_carga_hasta = (criterios.getFecha_carga_hasta() != null) ? criterios.getFecha_carga_hasta().toString() : null;
        String lugar = criterios.getLugar();
        String fecha_acontecimiento_desde = (criterios.getFecha_acontecimiento_desde() != null) ? criterios.getFecha_acontecimiento_desde().toString() : null;
        String fecha_acontecimiento_hasta = (criterios.getFecha_acontecimiento_hasta() != null) ? criterios.getFecha_acontecimiento_hasta().toString() : null;
        String origen = (criterios.getOrigen_carga() != null) ? criterios.getOrigen_carga().name() : null;
        String titulo = criterios.getTitulo();

        return new CriteriosDTO(categoria, multimedia, fecha_carga_desde, fecha_carga_hasta, fecha_acontecimiento_desde, fecha_acontecimiento_hasta, origen, titulo, lugar);
    }

    public void agregarHechosAColeccion(List<Hecho> hechos, Coleccion coleccion) {
        coleccion.agregarHechos(hechos);
    }

    public List<HechoDTOInput> setearOrigenCarga(List<HechoDTOInput> hechosDTO, OrigenCarga origenCarga) {
        for (HechoDTOInput hechoDTO : hechosDTO) {
            hechoDTO.setOrigen_carga(origenCarga.name());
        }

        return hechosDTO;
    }

    public void cargarColeccionConHechos(Long coleccionId) {

    }
}