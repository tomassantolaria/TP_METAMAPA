package com.TP_Metamapa.Servicio;

import com.TP_Metamapa.DTOS.*;
import com.TP_Metamapa.DTOS.UserDataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final String baseUrl;

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    public AuthService(@Value("${base-url.auth}") String url, RestTemplate restTemplate) {
        this.baseUrl = url;
        this.restTemplate = restTemplate;
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public KeycloakTokenDTO login(LoginDTO loginDTO) {
        System.out.println("llegue al service login");
        System.out.println(loginDTO.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTO, headers);
        String urlCompleta = baseUrl.concat("/auth/iniciar-sesion");
        System.out.println("peticion a : " + urlCompleta);

        try {
            ResponseEntity<KeycloakTokenDTO> respuesta = restTemplate.exchange(
                    urlCompleta,
                    HttpMethod.POST,
                    requestEntity,
                    KeycloakTokenDTO.class
            );
            System.out.println("antes del return: " + respuesta.getBody());
            return respuesta.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con el backend para crear hecho: " + e.getMessage(), e);
        }
    }

    public RoleDTO getRole(String accessToken) {
        String urlCompleta = baseUrl.concat("/auth/role");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        System.out.println("Solicitando roles...");
        try {
            ResponseEntity<RoleDTO> respuesta = restTemplate.exchange(
                    urlCompleta,
                    HttpMethod.GET,
                    requestEntity,
                    RoleDTO.class
            );
            System.out.println("roles recibidos");
            return respuesta.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con el backend para crear hecho: " + e.getMessage(), e);
        }
    }

    public String register(RegisterDTO registerDTO) {
        return webClient.post()
                .uri("/auth/create")
                .bodyValue(registerDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public UserDataDTO getUserData(String username, String accessToken) {
        String urlCompleta = baseUrl.concat("/auth/search/" + username);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Agregar token Bearer

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List> respuesta = restTemplate.exchange(
                    urlCompleta,
                    HttpMethod.GET,
                    requestEntity,
                    List.class
            );

            List<Map<String, Object>> users = respuesta.getBody();
            if (users != null && !users.isEmpty()) {
                Map<String, Object> userMap = users.get(0);

                UserDataDTO userData = new UserDataDTO();
                userData.setUsername((String) userMap.get("username"));
                userData.setFirstName((String) userMap.get("firstName"));
                userData.setLastName((String) userMap.get("lastName"));
                userData.setEmail((String) userMap.get("email"));
                userData.setAttributes((Map<String, Object>) userMap.get("attributes"));

                return userData;
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos del usuario: " + e.getMessage(), e);
        }
    }

}
