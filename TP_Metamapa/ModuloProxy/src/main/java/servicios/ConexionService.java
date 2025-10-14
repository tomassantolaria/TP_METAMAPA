package servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;



@Service
public class ConexionService implements IConexionService {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta) {
        try {
            String urlStr = url.toString();
            if (fechaUltimaConsulta != null) {
                urlStr += "?ultima_consulta=" + fechaUltimaConsulta.toString();
            }
            ResponseEntity<Map> response = restTemplate.getForEntity(urlStr, Map.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            //return ResponseEntity.status(500).body("url incorrecta");
        }
    }
}




//@Override
//public Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta) {
//    try {
//        // Construcción segura de la URI con parámetros
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(url.toURI());
//
//        if (fechaUltimaConsulta != null) {
//            builder.queryParam("ultima_consulta", fechaUltimaConsulta.toString());
//        }
//
//        // Realiza la request con método GET y espera un Map<String, Object> como respuesta
//        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
//                builder.build().toUri(),                 // URL armada
//                HttpMethod.GET,                          // método GET
//                null,                                    // sin headers ni body
//                new ParameterizedTypeReference<>() {}    // tipo esperado
//        );
//
//        return response.getBody();
//
//    } catch (URISyntaxException e) {
//        throw new IllegalArgumentException("URL mal formada: " + url.toString(), e);
//    } catch (RestClientException e) {
//        throw new RuntimeException("Error al conectar con la fuente externa: " + e.getMessage(), e);
//    }
//}

//@Service
//public class ConexionService implements IConexionService {
//
//    private final
//
//    @Override
//    public Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta) {
//        Map<String, Object> hechos = new HashMap<>();
//        // Implementar la lógica para obtener el siguiente hecho desde la URL
//        return hechos;
//
//        // api mock
//        // pipedream
//
//    }
//}