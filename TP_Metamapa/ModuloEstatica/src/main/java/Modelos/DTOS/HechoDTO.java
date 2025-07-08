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
    public LocalDate fechaAcontecimiento;
    public String lugar = null; // no sabemos el nomrbe del lugar
    public Double latitud;
    public Double longitud;

    public HechoDTO(String titulo, String descripcion, String categoria, LocalDate fechaAcontecimiento, Double latitud, Double longitud) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}