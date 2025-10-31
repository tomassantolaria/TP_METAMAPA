package Modelos.DTOs;

import Modelos.Entidades.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Esto porque cuando el administrador acepta o rechaza una solicitud al controller llegara un json que en su cuerpo tendra "ACEPTADA" o "RECHAZADA"
public class EstadoDTO {
    private String estado;

    public EstadoDTO(String estado){
        this.estado = estado;
    }
    public EstadoDTO(){}
}