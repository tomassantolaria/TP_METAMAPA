package Servicio;


import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.FiltrarRequestDTO;
import Modelos.DTOs.HechoDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgregadorServicio {

    @Autowired
    RestTemplate restTemplate;
    HechoRepositorio hechoRepositorio;
    ColeccionRepositorio coleccionRepositorio;

    public void actualizarHechos() {

        UriComponentsBuilder urlDinamica = UriComponentsBuilder.fromPath("http://dinamica/hechos/obtener");

        UriComponentsBuilder urlDemo = UriComponentsBuilder.fromPath("http://demo/hechos");

        UriComponentsBuilder urlEstatica = UriComponentsBuilder.fromPath("http://fuenteEstatica/hechos");

        UriComponentsBuilder urlMetamapa = UriComponentsBuilder.fromPath("http://metamapa/hechos");

        ResponseEntity<List<HechoDTO>> respuestaDinamica = restTemplate.exchange(
                urlDinamica.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        ResponseEntity<List<HechoDTO>> respuestaDemo = restTemplate.exchange(
                urlDemo.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        ResponseEntity<List<HechoDTO>> respuestaEstatica = restTemplate.exchange(
                urlEstatica.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        ResponseEntity<List<HechoDTO>> respuestaMetamapa = restTemplate.exchange(
                urlMetamapa.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        List<Hecho> hechos = new ArrayList<>();
        List<HechoDTO> hechosDTOTotales = new ArrayList<>();


        if (!respuestaDemo.getBody().isEmpty()) {
            List<HechoDTO> hechosDemo = this.setearOrigenCarga(respuestaDemo.getBody(), OrigenCarga.FUENTE_PROXY);
            hechosDTOTotales.addAll(hechosDemo) ;
            List<Hecho> hechosDemoTransformados = transaformarAHecho(hechosDemo, UUID.randomUUID());
            hechos.addAll(hechosDemoTransformados);
        }

        if (!respuestaDinamica.getBody().isEmpty()) {
            List<HechoDTO> hechosDinamica = this.setearOrigenCarga(respuestaDinamica.getBody(), OrigenCarga.FUENTE_DINAMICA);
            hechosDTOTotales.addAll(hechosDinamica);
            List<Hecho> hechosDinamicaTransformados = transaformarAHecho(hechosDinamica, UUID.randomUUID());
            hechos.addAll(hechosDinamicaTransformados);
        }

        if (!respuestaEstatica.getBody().isEmpty()) {
            List<HechoDTO> hechosEstatica = this.setearOrigenCarga(respuestaEstatica.getBody(), OrigenCarga.FUENTE_ESTATICA);
            hechosDTOTotales.addAll(hechosEstatica) ;
            List<Hecho> hechosEstaticaTransformados = transaformarAHecho(hechosEstatica, UUID.randomUUID());
            hechos.addAll(hechosEstaticaTransformados);
        }

        if (!respuestaMetamapa.getBody().isEmpty()) {
            List<HechoDTO> hechosMetamapa = this.setearOrigenCarga(respuestaMetamapa.getBody(), OrigenCarga.FUENTE_PROXY);
            hechosDTOTotales.addAll(hechosMetamapa) ;
            List<Hecho> hechosMetamapaTransformados = transaformarAHecho(hechosMetamapa, UUID.randomUUID());
            hechos.addAll(hechosMetamapaTransformados);
        }
        this.guardarHechos(hechos);
        this.actualizarColecciones(hechosDTOTotales);
    }

    public List<Hecho> transaformarAHecho(List<HechoDTO> hechosDTO, UUID idFuente) {
        return hechosDTO.stream()
                .map(hechoDTO -> {
                    Categoria categoria = Categoria.getInstance(hechoDTO.getCategoria());
                    Contenido contenido = new Contenido(hechoDTO.getContenido(), hechoDTO.getContenido_multimedia());
                    Ubicacion ubicacion = new Ubicacion(hechoDTO.getLugar(), hechoDTO.getLatitud(), hechoDTO.getLongitud());

                    Hecho hecho = new Hecho(UUID.randomUUID(), idFuente, hechoDTO.getTitulo(), hechoDTO.getDescripcion(), contenido, categoria,
                            hechoDTO.getFechaAcontecimiento(), ubicacion, LocalDate.now(), OrigenCarga.valueOf(hechoDTO.getOrigen_carga().toUpperCase()) , hechoDTO.getVisible(), hechoDTO.getUsuario(), hechoDTO.getAnonimo());
                    return hecho;
                })
                .toList();
    }

    public void guardarHechos(List<Hecho> hechos) {
        for (Hecho hecho : hechos) {
            hechoRepositorio.agregarHecho(hecho);
        }
    }

    public void actualizarColecciones(List<HechoDTO> hechosDTO) {

       for (Coleccion coleccion : coleccionRepositorio.getTodas()){
           CriteriosDTO criteriosDTO = this.transformarCriteriosADTO(coleccion.getCriterio_pertenencia());

           RestTemplate restTemplate = new RestTemplate();

           FiltrarRequestDTO request = new FiltrarRequestDTO(criteriosDTO, hechosDTO);

           ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                   "http://localhost:8080/filtrar", // URL de tu API
                   HttpMethod.POST,
                   new HttpEntity<>(request),
                   new ParameterizedTypeReference<>() {}
           );

           List<HechoDTO> hechosFiltrados = response.getBody();
           List<Hecho> hechos = this.transaformarAHecho(hechosFiltrados, UUID.randomUUID());
           agregarHechosAColeccion(hechos, coleccion);
       }
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

    public List<HechoDTO> setearOrigenCarga(List<HechoDTO> hechosDTO, OrigenCarga origenCarga) {
        for (HechoDTO hechoDTO : hechosDTO) {
            hechoDTO.setOrigen_carga(origenCarga.name());
        }

        return hechosDTO;
    }
}