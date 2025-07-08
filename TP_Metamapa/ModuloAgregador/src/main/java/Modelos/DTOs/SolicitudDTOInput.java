package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
//Esta es la solicitud de eliminación que recibo de un usuario
public class SolicitudDTOInput {
    private String motivo;
    private UUID idHecho;

    public SolicitudDTOInput(String motivo, UUID idHecho) {
        this.motivo = motivo;
        this.idHecho = idHecho;
    }
}