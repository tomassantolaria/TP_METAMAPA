package Modelos.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
//PATRON DTO
public class HechoDTO {
    private String titulo;
    private String descripcion;
    private String contenido;
    private String contenido_multimedia;
    private String categoria;
    private String fecha;
    private String lugar;
    private String usuario;
    private String anonimo;
}
