package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
//Esta es la solicitud que le vamos a mostrar al administrador cuando pida solicitudes pendientes
public class SolicitudDTOOutput {
    private String idSolicitud;
    private String motivo;
    private UUID idHecho;
    private LocalDate fechaCreacion;

    public SolicitudDTOOutput(String idSolicitud, String motivo, UUID idHecho, LocalDate fechaCreacion){
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.idHecho = idHecho;
        this.fechaCreacion = fechaCreacion;
    }
}