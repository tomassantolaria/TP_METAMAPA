package Modelos.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Hecho {

    private String titulo;
    private String descripcion;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private LocalDate fechaAcontecimiento;

    public Hecho(String titulo, String descripcion,  String categoria, LocalDate fechaAcontecimiento,  Double longitud, Double latitud) {
        this.titulo = titulo;
        this.longitud = longitud;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.latitud = latitud;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

}
