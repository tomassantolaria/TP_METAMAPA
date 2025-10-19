package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.SolicitudDTO;
import com.TP_Metamapa.DTOS.SolicitudDTOInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
@Service
public class SolicitudServicio {

    @Autowired
    RestTemplate restTemplate;


    public List<SolicitudDTO> obtenerPendientes(){
        UriComponentsBuilder urlSolicitudes = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/solicitudes/pendientes");
        ResponseEntity<List<SolicitudDTO>> respuesta = restTemplate.exchange(
                urlSolicitudes.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SolicitudDTO>>() {}
        );
        return respuesta.getBody();
    }

    public String crearSolicitud(SolicitudDTOInput solicitudDTO){
        UriComponentsBuilder urlSolicitudes = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/solicitudes");
        HttpEntity<SolicitudDTOInput> requestEntity = new HttpEntity<>(solicitudDTO);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    urlSolicitudes.toUriString(),
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            return response.getBody();

        } catch (HttpClientErrorException e) {
            String errorMessageFromApi = e.getResponseBodyAsString();
            throw new RuntimeException("Error 400 capturado. Mensaje: " + errorMessageFromApi);

        } catch (Exception e) {
            System.err.println("Error fatal en la llamada a la API: " + e.getMessage());
            throw new RuntimeException("No se pudo contactar con el servicio.", e);
        }
    }
}
