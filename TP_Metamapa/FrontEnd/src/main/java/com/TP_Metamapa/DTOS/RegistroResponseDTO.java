package com.TP_Metamapa.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroResponseDTO {
    private String keycloakUserId;
    private String mensaje;
    private int httpStatus;

    public RegistroResponseDTO(String keycloakUserId, String mensaje, int httpStatus) {
        this.keycloakUserId = keycloakUserId;
        this.mensaje = mensaje;
        this.httpStatus = httpStatus;
    }
}