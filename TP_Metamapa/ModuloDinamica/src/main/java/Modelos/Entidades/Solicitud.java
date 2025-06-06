package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Solicitud {
    LocalDate fecha_creacion; //date
    String motivo;
    Integer idHecho; //Supongo que lo mejor sería que la solicitud ingrese el id del hecho y no el hecho entero. Consultar
    Estado estado;

    public Solicitud(LocalDate fecha_creacion, String motivo, int idHecho, Estado estado){
        this.fecha_creacion = fecha_creacion;
        this.motivo = motivo;
        this.idHecho = idHecho;
        this.estado = estado;
    }
}
