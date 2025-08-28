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
    private String fuente; // path ???
    private String categoria;
    private Double latitud;
    private Double longitud;
    private LocalDate fechaAcontecimiento;
    private Boolean procesado;

    public Hecho(String titulo, String descripcion,  String fuente, String categoria, LocalDate fechaAcontecimiento,  Double longitud, Double latitud, Boolean procesado) {
        this.titulo = titulo;
        this.longitud = longitud;
        this.fuente = fuente;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.latitud = latitud;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.procesado = procesado;
    }

}
