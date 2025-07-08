package Servicios.impl;

import Modelos.DTOs.SolicitudDTO;
import Modelos.DTOs.HechoDTO;
import Servicios.IFuenteMetaMapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.util.List;



@Service  //CONSUMO DE API CON REST TEMPLATE
public class FuenteMetaMapaService implements IFuenteMetaMapaService {


    @Autowired
    private RestTemplate restTemplate;


    public List<HechoDTO> obtenerHechos(String categoria,
                                        String fecha_reporte_desde,
                                        String fecha_reporte_hasta,
                                        String fecha_acontecimiento_desde,
                                        String fecha_acontecimiento_hasta,
                                        String ubicacion) {
        UriComponentsBuilder url = UriComponentsBuilder // clase de Spring que ayuda a construir URLs
                .fromPath("http://agregador/hechos")
                .queryParam("categoria", categoria)
                .queryParam("fecha_reporte_desde", fecha_reporte_desde)
                .queryParam("fecha_reporte_hasta", fecha_reporte_hasta)
                .queryParam("fecha_acontecimiento_desde", fecha_acontecimiento_desde)
                .queryParam("fecha_acontecimiento_hasta", fecha_acontecimiento_hasta)
                .queryParam("ubicacion", ubicacion); //Localhost deberia remplazarse por la instancia de MetaMapa


        ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                url.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );


        return response.getBody();
    }




    public List<HechoDTO> obtenerHechosPorColeccion(String idColeccion,
                                                    String categoria,
                                                    String fecha_reporte_desde,
                                                    String fecha_reporte_hasta,
                                                    String fecha_acontecimiento_desde,
                                                    String fecha_acontecimiento_hasta,
                                                    String ubicacion) {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromPath("http://agregador/colecciones/" + idColeccion + "/hechos")
                .queryParam("categoria", categoria)
                .queryParam("fecha_reporte_desde", fecha_reporte_desde)
                .queryParam("fecha_reporte_hasta", fecha_reporte_hasta)
                .queryParam("fecha_acontecimiento_desde", fecha_acontecimiento_desde)
                .queryParam("fecha_acontecimiento_hasta", fecha_acontecimiento_hasta)
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
                .fromPath("http://localhost/solicitudes"); // Localhost deberia remplazarse por la instancia de MetaMapa

        ResponseEntity<String> response = restTemplate.postForEntity(
                url.toUriString(), solicitud, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error al crear la solicitud: " + response.getBody());
        }
    }

}