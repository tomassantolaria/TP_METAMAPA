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
public class ProvinciaServicio {

    @Autowired
    RestTemplate restTemplate;

    @Value("${url.publico}")
    private  String apiBaseUrl;

    public List<String> getProvinciasUnicas(){
        UriComponentsBuilder urlprovincias = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/publico/provincias");

        ResponseEntity<List<String>> respuesta = restTemplate.exchange(
                urlprovincias.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );
        return respuesta.getBody();

    }
}
