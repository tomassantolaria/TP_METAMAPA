package Servicios.impl;

import Modelos.DTOs.HechoDTO;
import Modelos.DTOs.SolicitudDTO;
import Servicios.IFuenteMetaMapaService;
import org.springframework.beans.factory.annotation.Autowired;
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
                .fromPath("http://localhost/hechos")
                .queryParam("categoria", categoria)
                .queryParam("fecha_reporte_desde", fecha_reporte_desde)
                .queryParam("fecha_reporte_hasta", fecha_reporte_hasta)
                .queryParam("fecha_acontecimiento_desde", fecha_acontecimiento_desde)
                .queryParam("fecha_acontecimiento_hasta", fecha_acontecimiento_hasta)
                .queryParam("ubicacion", ubicacion); //Localhost deberia remplazarse por la instancia de MetaMapa


        ResponseEntity<HechoDTO[]> response = restTemplate.getForEntity(url.toUriString(), HechoDTO[].class);
        // Se realiza una solicitud GET a la URL construida y se espera una respuesta que contenga un array de HechoDTO
        if (response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }
        return List.of();// La respuesta se convierte en una lista de HechoDTO
    }




    public List<HechoDTO> obtenerHechosPorColeccion(String idColeccion,
                                                    String categoria,
                                                    String fecha_reporte_desde,
                                                    String fecha_reporte_hasta,
                                                    String fecha_acontecimiento_desde,
                                                    String fecha_acontecimiento_hasta,
                                                    String ubicacion) {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromPath("http://localhost/colecciones/" + idColeccion + "/hechos")
                .queryParam("categoria", categoria)
                .queryParam("fecha_reporte_desde", fecha_reporte_desde)
                .queryParam("fecha_reporte_hasta", fecha_reporte_hasta)
                .queryParam("fecha_acontecimiento_desde", fecha_acontecimiento_desde)
                .queryParam("fecha_acontecimiento_hasta", fecha_acontecimiento_hasta)
                .queryParam("ubicacion", ubicacion);



        ResponseEntity<HechoDTO[]> response = restTemplate.getForEntity(
                url.toUriString(), HechoDTO[].class);

        if (response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }
        return List.of();
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


