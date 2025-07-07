package Modelos.DTOs;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HechoDTOInput {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenidoMultimedia;
    public String categoria;
    public LocalDate fecha;
    public String lugar;
    public Double latitud;
    public Double longitud;
}
