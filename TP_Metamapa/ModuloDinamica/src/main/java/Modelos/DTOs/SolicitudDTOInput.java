package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Esta es la solicitud de eliminación que recibo de un usuario
public class SolicitudDTOInput {
    private String motivo;
    private String idHecho;

    public SolicitudDTOInput(String motivo, String idHecho) {
        this.motivo = motivo;
        this.idHecho = idHecho;
    }
}
