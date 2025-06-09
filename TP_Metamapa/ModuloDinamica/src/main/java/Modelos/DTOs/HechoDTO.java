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
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public String fecha;
    public String lugar;
    public String usuario;
    public String anonimo;
}
