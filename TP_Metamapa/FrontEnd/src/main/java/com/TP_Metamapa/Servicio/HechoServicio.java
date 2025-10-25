package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.HechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HechoServicio {
    @Autowired
    RestTemplate restTemplate;

    public List<HechoDTO> hechosRecientes(){
        UriComponentsBuilder urlHechos = UriComponentsBuilder.fromHttpUrl("http://localhost:8087/publico/hechos");

        ResponseEntity<List<HechoDTO>> respuesta = restTemplate.exchange(
                urlHechos.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HechoDTO>>() {}
        );
        return obtenerCuatroMasRecientes(respuesta.getBody());

    }

    public static List<HechoDTO> obtenerCuatroMasRecientes(List<HechoDTO> hechos) {
        return hechos.stream()
                .sorted(Comparator.comparing(HechoDTO:: getFechaCarga).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public void eliminarHechoDeColeccion(Long idColeccion, Long idHecho){
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/coleccion/" + idColeccion + "/hecho" + idHecho);

        ResponseEntity<String> respuesta = restTemplate.exchange(
                url.toUriString(),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {}
        );
    }


}
