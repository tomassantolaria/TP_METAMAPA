package Modelos.Entidades;

import java.time.LocalDateTime ;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "CriteriosDePertenencia")
public class CriteriosDePertenencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Boolean multimedia;

    @ManyToOne
    @JoinColumn()
    private Categoria categoria;
    private LocalDateTime  fecha_carga_desde;
    private LocalDateTime  fecha_carga_hasta;
    @ManyToOne
    @JoinColumn()
    private Ubicacion ubicacion;
    private LocalDateTime  fecha_acontecimiento_desde;
    private LocalDateTime  fecha_acontecimiento_hasta;
    @Enumerated(EnumType.STRING)
    @JoinColumn()
    private OrigenCarga origen;

    public CriteriosDePertenencia(String titulo, Boolean multimedia, Categoria categoria, LocalDateTime  fecha_carga_desde, LocalDateTime  fecha_carga_hasta, Ubicacion ubicacion, LocalDateTime  fecha_acontecimiento_desde, LocalDateTime  fecha_acontecimiento_hasta, OrigenCarga origen_carga) {
        this.titulo = titulo;
        this.multimedia = multimedia;
        this.categoria = categoria;
        this.fecha_carga_desde = fecha_carga_desde;
        this.fecha_carga_hasta = fecha_carga_hasta;
        this.ubicacion = ubicacion;
        this.fecha_acontecimiento_desde = fecha_acontecimiento_desde;
        this.fecha_acontecimiento_hasta = fecha_acontecimiento_hasta;
        this.origen = origen_carga;
    }
    public  CriteriosDePertenencia(){}

}