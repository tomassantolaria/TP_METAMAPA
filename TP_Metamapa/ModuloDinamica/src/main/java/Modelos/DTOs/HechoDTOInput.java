package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//PATRON DTO
public class HechoDTOInput {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public String fecha;
    public String lugar;
    public String usuario;
    public String anonimo;
}
