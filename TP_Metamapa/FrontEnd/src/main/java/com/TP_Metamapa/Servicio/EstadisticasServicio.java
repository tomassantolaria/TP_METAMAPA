package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.SolicitudDTO;
import com.TP_Metamapa.DTOS.UltimasEstadisticasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class EstadisticasServicio {

    @Autowired
    RestTemplate restTemplate;
    @Value("${url.estadisticas}")
    private String urlEstadistica;

    public UltimasEstadisticasDTO obtenerEstadisticas(){
        UriComponentsBuilder urlEstadisticas = UriComponentsBuilder.fromHttpUrl(urlEstadistica + "/estadisticas");
        ResponseEntity<UltimasEstadisticasDTO> respuesta = restTemplate.exchange(
                urlEstadisticas.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UltimasEstadisticasDTO>() {}
        );
        return respuesta.getBody();
    }

    public String exportarCSV(){
        UriComponentsBuilder urlEstadisticas = UriComponentsBuilder.fromHttpUrl(urlEstadistica+"/estadisticas/csv");
        ResponseEntity<String> respuesta = restTemplate.exchange(
                urlEstadisticas.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {}
        );
        return respuesta.getBody();
    }

}
