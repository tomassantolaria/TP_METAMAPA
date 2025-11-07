package com.keycloak.moduloauth.DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Value
@RequiredArgsConstructor
@Builder
@Data
public class GetTokenDTO {
    String client_id;
    String grant_type;
    String username;
    String password;
    String client_secret;
}
