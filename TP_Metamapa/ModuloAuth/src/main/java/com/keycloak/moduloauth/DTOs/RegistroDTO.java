package com.keycloak.moduloauth.DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@RequiredArgsConstructor
@Builder
@Data

public class RegistroDTO implements Serializable {
    String username;
    String email;
    String firstName;
    String lastName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate birthdate;
    String password;
}
