package Modelos.DTOs;


import Modelos.Entidades.OrigenCarga;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//PATRON DTO
public class HechoDTO {
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
    public Boolean anonimo;
    public Boolean visible;
    public Integer origen_carga;
}
