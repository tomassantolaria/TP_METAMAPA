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
import java.util.List;
import java.util.Optional;

@Service
public class NavegacionServicio {

    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8087/publico";

    public List<HechoDTO> buscarPorTextoLibre(String textoLibre) {
        String url = apiBaseUrl + "/buscar/" + textoLibre; // O la ruta que corresponda



        ResponseEntity<List<HechoDTO>> respuesta = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HechoDTO>>() {}
        );

        return respuesta.getBody();
    }

    public List<HechoDTO> buscarConFiltros(String categoria, Boolean contenidoMultimedia,
                                           LocalDate fechaCargaDesde, LocalDate fechaCargaHasta,
                                           LocalDate fechaHechoDesde, LocalDate fechaHechoHasta,
                                           String origen, String titulo,
                                           String pais, String provincia, String localidad,
                                           Long coleccionId, Boolean navegacionCurada) {
        String url;
        UriComponentsBuilder builder;

       if(coleccionId == null){

           url = apiBaseUrl + "/hechos";

         builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParamIfPresent("categoria", Optional.ofNullable(categoria))
                .queryParamIfPresent("contenidoMultimedia", Optional.ofNullable(contenidoMultimedia))
                .queryParamIfPresent("fechaCargaDesde", Optional.of(fechaCargaDesde.atStartOfDay()))
                .queryParamIfPresent("fechaCargaHasta", Optional.of(fechaCargaHasta.atTime(23, 59, 59)))
                .queryParamIfPresent("fechaHechoDesde", Optional.of(fechaHechoDesde.atStartOfDay()))
                .queryParamIfPresent("fechaHechoHasta", Optional.of(fechaHechoHasta.atTime(23, 59, 59)))
                .queryParamIfPresent("origen", Optional.ofNullable(origen))
                .queryParamIfPresent("titulo", Optional.ofNullable(titulo))
                .queryParamIfPresent("pais", Optional.ofNullable(pais))
                .queryParamIfPresent("provincia", Optional.ofNullable(provincia))
                .queryParamIfPresent("localidad", Optional.ofNullable(localidad));
        }else {
           url = apiBaseUrl + "/colecciones/" + coleccionId + "/hechos";

               builder = UriComponentsBuilder.fromHttpUrl(url)
                     .queryParamIfPresent("categoria", Optional.ofNullable(categoria))
                     .queryParamIfPresent("contenidoMultimedia", Optional.ofNullable(contenidoMultimedia))
                     .queryParamIfPresent("fechaCargaDesde", Optional.of(fechaCargaDesde.atStartOfDay()))
                     .queryParamIfPresent("fechaCargaHasta", Optional.of(fechaCargaHasta.atTime(23, 59, 59)))
                     .queryParamIfPresent("fechaHechoDesde", Optional.of(fechaHechoDesde.atStartOfDay()))
                     .queryParamIfPresent("fechaHechoHasta", Optional.of(fechaHechoHasta.atTime(23, 59, 59)))
                     .queryParamIfPresent("origen", Optional.ofNullable(origen))
                     .queryParamIfPresent("titulo", Optional.ofNullable(titulo))
                     .queryParamIfPresent("pais", Optional.ofNullable(pais))
                     .queryParamIfPresent("provincia", Optional.ofNullable(provincia))
                     .queryParamIfPresent("localidad", Optional.ofNullable(localidad))
                      .queryParamIfPresent("navegacionCurada", Optional.ofNullable(navegacionCurada));
       }

        ResponseEntity<List<HechoDTO>> respuesta = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HechoDTO>>() {}
        );

        return respuesta.getBody();
    }

    public HechoDTO obtenerHechoPorId(Long id) {
        String url = apiBaseUrl + "/hechos/" + id; // O la ruta que corresponda

        ResponseEntity<HechoDTO> respuesta = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                HechoDTO.class
        );

        return respuesta.getBody();
    }
}
