package Modelos.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//PATRON DTO
public class HechoDTO {

    public String titulo;
    public String descripcion;
    public String contenido = null;
    public String contenidoMultimedia = null;
    public String categoria;
    public LocalDate fechaCarga = null;
    public LocalDate fechaAcontecimiento;
    public String lugar = null; // no sabemos el nomrbe del lugar
    public Double latitud;
    public Double longitud;
    public String usuario = null;
    public String nombre = null;
    public String apellido  = null;
    public LocalDate fecha_nacimiento = null;
    public Boolean anonimo = null;
    public Boolean visible = null;
    public Integer origen_carga = null;


    public HechoDTO(String titulo, String descripcion, String categoria, LocalDate fechaAcontecimiento, Double latitud, Double longitud) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}