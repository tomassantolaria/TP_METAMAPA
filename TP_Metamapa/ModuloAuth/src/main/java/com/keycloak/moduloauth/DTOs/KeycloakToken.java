package com.keycloak.moduloauth.DTOs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data // Usa Data en lugar de Value para tener getters/setters flexibles
@AllArgsConstructor
@NoArgsConstructor // Jackson necesita un constructor vacío a veces
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeycloakToken {
    String access_token;
    Integer expires_in;
    Integer refresh_expires_in;
    String refresh_token;
    String token_type;
    Integer not_before_policy;
    String session_state;
    String scope;
}
