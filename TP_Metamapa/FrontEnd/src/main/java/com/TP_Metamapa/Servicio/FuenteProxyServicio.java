package com.TP_Metamapa.Servicio;


import com.TP_Metamapa.DTOS.FuentesDTO;
import com.TP_Metamapa.Modelos.TipoFuente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class FuenteProxyServicio {

    @Autowired
    RestTemplate restTemplate;

    public List<FuentesDTO> obtenerTodas(){
        UriComponentsBuilder urlProxy = UriComponentsBuilder.fromHttpUrl("http://localhost:8086/fuentes");

        ResponseEntity<List<FuentesDTO>> respuesta = restTemplate.exchange(
                urlProxy.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FuentesDTO>>() {}
        );
        return respuesta.getBody();

    }

    public void crear(String url, TipoFuente tipo){
        FuentesDTO fuente = new FuentesDTO(url, tipo);

        UriComponentsBuilder urlProxy = UriComponentsBuilder.fromHttpUrl("http://localhost:8086/fuentes");
        HttpEntity<FuentesDTO> requestEntity = new HttpEntity<>(fuente);
        ResponseEntity<String> respuesta = restTemplate.exchange(
                urlProxy.toUriString(),
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {}
        );

    }
}
