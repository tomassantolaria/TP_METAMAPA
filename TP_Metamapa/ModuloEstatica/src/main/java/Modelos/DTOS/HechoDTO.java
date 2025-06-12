package Modelos.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//PATRON DTO
public class HechoDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private LocalDate fechaAcontecimiento;

    public HechoDTO(String titulo, String descripcion, String categoria, Double latitud, Double longitud, LocalDate fechaAcontecimiento) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaAcontecimiento = fechaAcontecimiento;
    }
}
