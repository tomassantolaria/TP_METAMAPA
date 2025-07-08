package Servicio;


import Modelos.DTOs.HechoDTO;
import Modelos.Entidades.*;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgregadorServicio {

    @Autowired
    private RestTemplate restTemplate;
    private  HechoRepositorio hechoRepositorio;

    public void actualizarHechos() {

        UriComponentsBuilder urlDinamica = UriComponentsBuilder.fromPath("http://dinamica/hechos/obtener");

        UriComponentsBuilder urlDemo = UriComponentsBuilder.fromPath("http://demo/hechos");

        UriComponentsBuilder urlEstatica = UriComponentsBuilder.fromPath("http://fuenteEstatica/hechos");

        UriComponentsBuilder urlMetamapa = UriComponentsBuilder.fromPath("http://metamapa/hechos");

        ResponseEntity<List<HechoDTO>> respuestaDinamica = restTemplate.exchange(
                urlDinamica.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        ResponseEntity<List<HechoDTO>> respuestaDemo = restTemplate.exchange(
                urlDemo.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        ResponseEntity<List<HechoDTO>> respuestaEstatica = restTemplate.exchange(
                urlEstatica.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        ResponseEntity<List<HechoDTO>> respuestaMetamapa = restTemplate.exchange(
                urlMetamapa.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );


        List<Hecho> hechos = new ArrayList<>();


        if (!respuestaDemo.getBody().isEmpty()) {
            List<HechoDTO> hechosDemo = respuestaDemo.getBody();
            List<Hecho> hechosDemoTransformados = transaformarAHecho(hechosDemo, OrigenCarga.FUENTE_PROXY, 1);
            hechos.addAll(hechosDemoTransformados);
        }

        if (!respuestaDinamica.getBody().isEmpty()) {
            List<HechoDTO> hechosDinamica = respuestaDinamica.getBody();
            List<Hecho> hechosDinamicaTransformados = transaformarAHecho(hechosDinamica, OrigenCarga.FUENTE_DINAMICA, 2);
            hechos.addAll(hechosDinamicaTransformados);
        }

        if (!respuestaEstatica.getBody().isEmpty()) {
            List<HechoDTO> hechosEstatica = respuestaEstatica.getBody();
            List<Hecho> hechosEstaticaTransformados = transaformarAHecho(hechosEstatica, OrigenCarga.FUENTE_ESTATICA, 3);
            hechos.addAll(hechosEstaticaTransformados);
        }

        if (!respuestaMetamapa.getBody().isEmpty()) {
            List<HechoDTO> hechosMetamapa = respuestaMetamapa.getBody();
            List<Hecho> hechosMetamapaTransformados = transaformarAHecho(hechosMetamapa, OrigenCarga.FUENTE_PROXY, 4);
            hechos.addAll(hechosMetamapaTransformados);
        }

        this.guardarHechos(hechos);
    }

    public List<Hecho> transaformarAHecho(List<HechoDTO> hechosDTO, OrigenCarga origenCarga, Integer idFuente) {
        return hechosDTO.stream()
                .map(hechoDTO -> {
                    Categoria categoria = Categoria.getInstance(hechoDTO.getCategoria());
                    Contenido contenido = new Contenido(hechoDTO.getContenido(), hechoDTO.getContenido_multimedia());
                    Ubicacion ubicacion = new Ubicacion(hechoDTO.getLugar(), hechoDTO.getLatitud(), hechoDTO.getLongitud());

                    Hecho hecho = new Hecho(UUID.randomUUID(), idFuente, hechoDTO.getTitulo(), hechoDTO.getDescripcion(), contenido, categoria,
                            hechoDTO.getFechaAcontecimiento(), ubicacion, LocalDate.now(), origenCarga, hechoDTO.getVisible(), hechoDTO.getUsuario(), hechoDTO.getAnonimo());
                    return hecho;
                })
                .toList();
    }

    public void guardarHechos(List<Hecho> hechos) {
        for (Hecho hecho : hechos) {
            hechoRepositorio.agregarHecho(hecho);
            // a chequear como meterlo en colecciones
        }
    }
}