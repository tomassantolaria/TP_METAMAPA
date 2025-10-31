package Modelos.Entidades;

import java.time.LocalDateTime ;
import java.time.LocalDateTime ;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "CriteriosDePertenencia")
public class CriteriosDePertenencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String titulo;
    Boolean multimedia;
    @ManyToOne
    @JoinColumn()
    Categoria categoria;
    LocalDateTime  fechaCargaDesde;
    LocalDateTime  fechaCargaHasta;
    @ManyToOne
    @JoinColumn()
    Ubicacion ubicacion;

    LocalDateTime fechaAcontecimientoDesde;
    LocalDateTime fechaAcontecimientoHasta;

    @Enumerated(EnumType.STRING)
    @Column()
    OrigenCarga origen;

    public CriteriosDePertenencia(String titulo, Boolean multimedia, Categoria categoria, LocalDateTime  fechaCargaDesde, LocalDateTime  fechaCargaHasta, Ubicacion ubicacion, LocalDateTime fechaAcontecimientoDesde, LocalDateTime fechaAcontecimientoHasta, OrigenCarga origen_carga) {
        this.titulo = titulo;
        this.multimedia = multimedia;
        this.categoria = categoria;
        this.fechaCargaDesde = fechaCargaDesde;
        this.fechaCargaHasta = fechaCargaHasta;
        this.ubicacion = ubicacion;
        this.fechaAcontecimientoDesde = fechaAcontecimientoDesde;
        this.fechaAcontecimientoHasta = fechaAcontecimientoHasta;
        this.origen = origen_carga;
    }
    public  CriteriosDePertenencia(){}
}