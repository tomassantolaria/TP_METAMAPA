package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudDTO {
    String fecha_creacion;
    String motivo;
    String idHecho;

    public SolicitudDTO(String fecha_creacion, String motivo, String idHecho) {
        this.fecha_creacion = fecha_creacion;
        this.motivo = motivo;
        this.idHecho = idHecho;
    }
}
