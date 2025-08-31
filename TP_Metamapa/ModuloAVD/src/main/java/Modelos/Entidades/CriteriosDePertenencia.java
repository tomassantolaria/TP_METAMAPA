package Modelos.Entidades;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "CriteriosDePertencia")
public class CriteriosDePertenencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Boolean multimedia;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private LocalDate fecha_carga_desde;
    private LocalDate fecha_carga_hasta;
    private Ubicacion ubicacion;
    private LocalDate fecha_acontecimiento_desde;
    private LocalDate fecha_acontecimiento_hasta;
    @Enumerated(EnumType.STRING)
    @Column(name = "origen_carga")
    private OrigenCarga origen_carga;

    public CriteriosDePertenencia(String titulo, Boolean multimedia, Categoria categoria, LocalDate fecha_carga_desde, LocalDate fecha_carga_hasta, Ubicacion ubicacion, LocalDate fecha_acontecimiento_desde, LocalDate fecha_acontecimiento_hasta, OrigenCarga origen_carga) {
        this.titulo = titulo;
        this.multimedia = multimedia;
        this.categoria = categoria;
        this.fecha_carga_desde = fecha_carga_desde;
        this.fecha_carga_hasta = fecha_carga_hasta;
        this.ubicacion = ubicacion;
        this.fecha_acontecimiento_desde = fecha_acontecimiento_desde;
        this.fecha_acontecimiento_hasta = fecha_acontecimiento_hasta;
        this.origen_carga = origen_carga;
    }
    public  CriteriosDePertenencia(){}

}