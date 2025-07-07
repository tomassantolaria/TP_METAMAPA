package Modelos.DTOs;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class HechoDTOInput {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public LocalDate fechaAcontecimiento;
    public LocalDate fechaCarga;
    public String lugar;
    public Double latitud;
    public Double longitud;
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;
}