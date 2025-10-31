package Modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Esta es la solicitud de eliminación que recibo de un usuario
public class SolicitudDTOInput {
    private String motivo;
    private Long idHecho;

    public SolicitudDTOInput(String motivo, Long idHecho) {
        this.motivo = motivo;
        this.idHecho = idHecho;
    }

    public SolicitudDTOInput(){}
}