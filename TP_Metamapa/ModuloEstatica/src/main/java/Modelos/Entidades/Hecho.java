package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Hecho")
public class Hecho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Cambiar a long

    private String titulo;
    private String descripcion;
    @ManyToOne()
    @JoinColumn
    private Archivo archivo; // ID
    private String categoria;
    private String latitud;
    private String longitud;
    private LocalDate fechaAcontecimiento;
    private Boolean procesado;

    public Hecho(Boolean procesado, LocalDate fechaAcontecimiento, String longitud, String latitud, String categoria, Archivo archivo, String descripcion, String titulo) {
        this.procesado = procesado;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.longitud = longitud;
        this.latitud = latitud;
        this.categoria = categoria;
        this.archivo = archivo;
        this.descripcion = descripcion;
        this.titulo = titulo;
    }

    public Hecho() {

    }
}
