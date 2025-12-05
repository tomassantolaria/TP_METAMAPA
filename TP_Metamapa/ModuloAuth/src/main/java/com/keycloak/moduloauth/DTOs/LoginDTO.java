package com.keycloak.moduloauth.DTOs;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginDTO {
    String username;
    String password;
}
