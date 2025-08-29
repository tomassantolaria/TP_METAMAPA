package Servicio;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.FiltrarRequestDTO;
import Modelos.Entidades.*;
import Modelos.DTOs.HechoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavegadorServicio {


    public List<HechoDTO> filtrarHechos(List<Hecho> hechos, String categoria, String contenidoMultimedia, String fechaCargaDesde, String fechaCargaHasta, String fechaHechoDesde, String fechaHechoHasta, String titulo, String ubicacion, String origenCarga) {
        List<HechoDTO> hechoDTOS = transformarADTOLista(hechos);
        CriteriosDTO criteriosDTO = new CriteriosDTO(categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCarga, titulo, ubicacion);
        RestTemplate restTemplate = new RestTemplate();

        FiltrarRequestDTO request = new FiltrarRequestDTO(criteriosDTO, hechoDTOS);

        ResponseEntity<List<HechoDTO>> response = restTemplate.exchange(
                "http://localhost:8080/filtrar", // URL de tu API
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public List<HechoDTO> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTO> hechosDTO;
        hechosDTO = hechos.stream()
                .map(this::transformarAHechoDTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTO transformarAHechoDTO (Hecho hecho){
        HechoDTO hechoDTO = new HechoDTO(hecho.getTitulo(),hecho.getDescripcion(), hecho.getContenido().getTexto(),hecho.getContenido().getContenido_multimedia(),hecho.getCategoria().getNombre(), hecho.getFecha(), hecho.getFecha_carga(), hecho.getUbicacion().getNombre(), hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud(), hecho.getUsuario(), null, null, null, null, null, hecho.getOrigen_carga().name());
        if (hecho.isAnonimo()) {
            hechoDTO.setUsuario(null);
        }
        return hechoDTO;
    }
}
