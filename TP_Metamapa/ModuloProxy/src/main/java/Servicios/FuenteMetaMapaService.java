package Servicios;

import Modelos.DTOs.SolicitudDTO;
import Modelos.DTOs.HechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service  //CONSUMO DE API CON REST TEMPLATE
public class FuenteMetaMapaService{


    @Autowired
    RestTemplate restTemplate;


    public List<HechoDTO> obtenerHechos(/*String categoria,
                                        String ContenidoMultimedia,
                                        String fecha_reporte_desde,
                                        String fecha_reporte_hasta,
                                        String fecha_acontecimiento_desde,
                                        String fecha_acontecimiento_hasta,
                                        String origen,
                                        String titulo,
                                        String pais,
                                        String provincia,
                                        String localidad*/) {
        UriComponentsBuilder url = UriComponentsBuilder // clase de Spring que ayuda a construir URLs
                .fromHttpUrl("http://localhost:8084/publico/hechos")/*
                .queryParamIfPresent("categoria", Optional.ofNullable(categoria))
                .queryParamIfPresent("contenidoMultimedia", Optional.ofNullable(ContenidoMultimedia))
                .queryParamIfPresent("fechaCargaDesde", Optional.ofNullable(fecha_reporte_desde))
                .queryParamIfPresent("fechaCargaHasta", Optional.ofNullable(fecha_reporte_hasta))
                .queryParamIfPresent("fechaHechoDesde", Optional.ofNullable(fecha_acontecimiento_desde))
                .queryParamIfPresent("fechaHechoHasta", Optional.ofNullable(fecha_acontecimiento_hasta))
                .queryParamIfPresent("origen", Optional.ofNullable(origen))
                .queryParamIfPresent("titulo", Optional.ofNullable(titulo))
                .queryParamIfPresent("pais", Optional.ofNullable(pais)) //Localhost deberia remplazarse por la instancia de MetaMapa
                .queryParamIfPresent("provincia", Optional.ofNullable(provincia))
                .queryParamIfPresent("localicad", Optional.ofNullable(localidad))*/;


        ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                url.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );


        return response.getBody();
    }




    /*public List<HechoDTO> obtenerHechosPorColeccion(String idColeccion,
                                                    String categoria,
                                                    String ContenidoMultimedia,
                                                    String fecha_reporte_desde,
                                                    String fecha_reporte_hasta,
                                                    String fecha_acontecimiento_desde,
                                                    String fecha_acontecimiento_hasta,
                                                    String origen,
                                                    String titulo,
                                                    String ubicacion) {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromPath("http://agregador/colecciones/" + idColeccion + "/hechos")
                .queryParam("categoria", categoria)
                .queryParam("contenido_multimedia", ContenidoMultimedia)
                .queryParam("fecha_reporte_desde", fecha_reporte_desde)
                .queryParam("fecha_reporte_hasta", fecha_reporte_hasta)
                .queryParam("fecha_acontecimiento_desde", fecha_acontecimiento_desde)
                .queryParam("fecha_acontecimiento_hasta", fecha_acontecimiento_hasta)
                .queryParam("origen", origen)
                .queryParam("titulo", titulo)
                .queryParam("ubicacion", ubicacion);



        ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                url.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        return response.getBody();
    }

    public void crearSolicitud(SolicitudDTO solicitud) throws Exception {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8084/solicitudes");

        ResponseEntity<String> response = restTemplate.postForEntity(
                url.toUriString(), solicitud, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error al crear la solicitud: " + response.getBody());
        }
    }
*/
}