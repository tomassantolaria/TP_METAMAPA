package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.ColeccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ColeccionServicio {

    @Autowired
    RestTemplate restTemplate;

    public List<ColeccionDTO> getColecciones(){
        UriComponentsBuilder urlColeccion = UriComponentsBuilder.fromHttpUrl("http://localhost:8087/publico/colecciones");

        ResponseEntity<List<ColeccionDTO>> respuesta = restTemplate.exchange(
                urlColeccion.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ColeccionDTO>>() {}
        );
        return respuesta.getBody();

    }

    public void eliminarColeccion(Long idColeccion) {
        UriComponentsBuilder urlEliminar = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/coleccion/" + idColeccion);

        ResponseEntity<String> respuesta = restTemplate.exchange(
                urlEliminar.toUriString(),
                HttpMethod.DELETE,
                null,
                String.class
        );
        // Ver si hacer algo con la respuesta
    }



    public ColeccionDTO obtenerColeccion(Long id){
        UriComponentsBuilder urlColeccion = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/coleccion/" + id);
        ResponseEntity<ColeccionDTO> respuesta = restTemplate.exchange(
                urlColeccion.toUriString(),
                HttpMethod.GET,
                null,
                ColeccionDTO.class
        );
        return respuesta.getBody();
    }

    public void actualizarColeccion(Long id, String consenso){
        UriComponentsBuilder urlColeccion = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/coleccion/" + id + "/Consenso/" + consenso);
        ResponseEntity<ColeccionDTO> respuesta = restTemplate.exchange(
                urlColeccion.toUriString(),
                HttpMethod.POST,
                null,
                ColeccionDTO.class
        );
    }
}