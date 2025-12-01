package com.TP_Metamapa.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class PaisServicio {

    @Autowired
    RestTemplate restTemplate;
    @Value("${url.publico}")
    private  String apiBaseUrl;

    public List<String> getPaisesUnicos(){
        UriComponentsBuilder urlpaises = UriComponentsBuilder.fromHttpUrl(apiBaseUrl +"/publico/paises");

        ResponseEntity<List<String>> respuesta = restTemplate.exchange(
                urlpaises.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );
        return respuesta.getBody();

    }
}
