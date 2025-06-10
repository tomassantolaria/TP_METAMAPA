package Modelos.DTOs;

import lombok.Getter;

@Getter
public class SolicitudDTOOutput {
    private String idSolicitud;
    private String motivo;
    private String idHecho;
    private String fechaCreacion;

    public SolicitudDTOOutput(String idSolicitud, String motivo, String idHecho, String fechaCreacion){
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.idHecho = idHecho;
        this.fechaCreacion = fechaCreacion;
    }
}
