package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.HechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NavegacionServicio {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${url.publico}")
    private  String apiBaseUrl;

    private static final String HECHOS_PATH = "/hechos/{id}";

    public List<HechoDTO> buscarPorTextoLibre(String textoLibre) {
        String url = apiBaseUrl + "/publico/buscar/" + textoLibre; // O la ruta que corresponda



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

            url = apiBaseUrl + "/publico/hechos";

            builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParamIfPresent("categoria", Optional.ofNullable(categoria).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("contenidoMultimedia", Optional.ofNullable(contenidoMultimedia))
                    .queryParamIfPresent("fechaCargaDesde", Optional.ofNullable(fechaCargaDesde).map(LocalDate::atStartOfDay))
                    .queryParamIfPresent("fechaCargaHasta", Optional.ofNullable(fechaCargaHasta).map(fecha -> fecha.atTime(23, 59, 59)))
                    .queryParamIfPresent("fechaHechoDesde", Optional.ofNullable(fechaHechoDesde).map(LocalDate::atStartOfDay))
                    .queryParamIfPresent("fechaHechoHasta",  Optional.ofNullable(fechaHechoHasta).map(fecha -> fecha.atTime(23, 59, 59)))
                    .queryParamIfPresent("origen", Optional.ofNullable(origen).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("titulo", Optional.ofNullable(titulo).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("pais", Optional.ofNullable(pais).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("provincia", Optional.ofNullable(provincia).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("localidad", Optional.ofNullable(localidad).filter(s -> !s.isBlank()));
        }else {
            url = apiBaseUrl + "/publico/colecciones/" + coleccionId + "/hechos";

            builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParamIfPresent("categoria", Optional.ofNullable(categoria).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("contenidoMultimedia", Optional.ofNullable(contenidoMultimedia))
                    .queryParamIfPresent("fechaCargaDesde", Optional.ofNullable(fechaCargaDesde).map(LocalDate::atStartOfDay))
                    .queryParamIfPresent("fechaCargaHasta", Optional.ofNullable(fechaCargaHasta).map(fecha -> fecha.atTime(23, 59, 59)))
                    .queryParamIfPresent("fechaHechoDesde", Optional.ofNullable(fechaHechoDesde).map(LocalDate::atStartOfDay))
                    .queryParamIfPresent("fechaHechoHasta",  Optional.ofNullable(fechaHechoHasta).map(fecha -> fecha.atTime(23, 59, 59)))
                    .queryParamIfPresent("origen", Optional.ofNullable(origen).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("titulo", Optional.ofNullable(titulo).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("pais", Optional.ofNullable(pais).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("provincia", Optional.ofNullable(provincia).filter(s -> !s.isBlank()))
                    .queryParamIfPresent("localidad", Optional.ofNullable(localidad).filter(s -> !s.isBlank()))
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

    public Optional<HechoDTO> obtenerHechoPorId(Long id) {
        try{
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID invÃ¡lido");
            }
            final String url = apiBaseUrl +"/publico/" +HECHOS_PATH; // O la ruta que corresponda

            ResponseEntity<HechoDTO> respuesta = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    HechoDTO.class,
                    id
            );

            return Optional.ofNullable(respuesta.getBody());

        }catch (HttpClientErrorException.NotFound e) {
            System.out.println("Hecho con ID " + id + " no encontrado en el backend.");
            return Optional.empty();
        }
    }
}