package com.keycloak.moduloauth.DTOs;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO implements Serializable {
    String username;
    String email;
    String firstName;
    String lastName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate birthdate;
    String password;
}
