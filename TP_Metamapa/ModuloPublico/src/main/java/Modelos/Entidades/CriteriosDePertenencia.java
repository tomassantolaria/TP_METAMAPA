package Modelos.Entidades;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CriteriosDePertenencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String titulo;
    Boolean multimedia;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    Categoria categoria;
    LocalDate fecha_carga_desde;
    LocalDate fecha_carga_hasta;
    String lugar;
    LocalDate fecha_acontecimiento_desde;
    LocalDate fecha_acontecimiento_hasta;
    @Enumerated(EnumType.STRING)
    @Column(name = "origen_carga")
    OrigenCarga origen_carga;

    public CriteriosDePertenencia(String titulo, Boolean multimedia, Categoria categoria, LocalDate fecha_carga_desde, LocalDate fecha_carga_hasta, String lugar, LocalDate fecha_acontecimiento_desde, LocalDate fecha_acontecimiento_hasta, OrigenCarga origen_carga) {
        this.titulo = titulo;
        this.multimedia = multimedia;
        this.categoria = categoria;
        this.fecha_carga_desde = fecha_carga_desde;
        this.fecha_carga_hasta = fecha_carga_hasta;
        this.lugar = lugar;
        this.fecha_acontecimiento_desde = fecha_acontecimiento_desde;
        this.fecha_acontecimiento_hasta = fecha_acontecimiento_hasta;
        this.origen_carga = origen_carga;
    }
    public  CriteriosDePertenencia(){}
}