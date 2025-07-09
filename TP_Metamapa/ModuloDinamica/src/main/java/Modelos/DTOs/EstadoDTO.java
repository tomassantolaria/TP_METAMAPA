package Modelos.DTOs;

import Modelos.Entidades.Estado;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
//Esto porque cuando el administrador acepta o rechaza una solicitud al controller llegara un json que en su cuerpo tendra "ACEPTADA" o "RECHAZADA"
public class EstadoDTO {
    private Estado estado;
}