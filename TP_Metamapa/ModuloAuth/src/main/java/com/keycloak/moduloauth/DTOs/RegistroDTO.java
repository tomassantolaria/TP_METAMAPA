package com.keycloak.moduloauth.DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@RequiredArgsConstructor
@Builder
@Data
public class RegistroDTO implements Serializable {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
