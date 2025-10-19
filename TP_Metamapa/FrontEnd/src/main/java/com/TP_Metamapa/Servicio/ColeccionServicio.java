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
}
