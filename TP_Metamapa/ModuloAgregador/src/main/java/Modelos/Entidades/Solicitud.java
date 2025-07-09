package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Solicitud {
    UUID idSolcitud;
    LocalDate fecha_creacion; //date
    String motivo;
    UUID idHecho; //Supongo que lo mejor sería que la solicitud ingrese el id del hecho y no el hecho entero. Consultar
    Estado estado;

    public Solicitud(UUID idSolicitud, LocalDate fecha_creacion, String motivo, UUID idHecho, Estado estado){
        this.idSolcitud = idSolicitud;
        this.fecha_creacion = fecha_creacion;
        this.motivo = motivo;
        this.idHecho = idHecho;
        this.estado = estado;
    }
}