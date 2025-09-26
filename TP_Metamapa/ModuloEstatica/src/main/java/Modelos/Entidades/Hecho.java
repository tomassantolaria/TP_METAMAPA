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
    @Column(precision = 9, scale = 5) // o 6
    private String latitud;
    @Column(precision = 9, scale = 5)
    private String longitud;
    private LocalDate fechaAcontecimiento;
    private Boolean procesado;

    public Hecho(String titulo, String descripcion,  Archivo archivo, String categoria, LocalDate fechaAcontecimiento,  String longitud, String latitud, Boolean procesado) {
        this.titulo = titulo;
        this.longitud = longitud;
        this.archivo = archivo;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.latitud = latitud;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.procesado = procesado;
    }


    public Hecho() {

    }
}
