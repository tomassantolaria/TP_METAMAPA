package Modelos.Entidades;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CriteriosDePertenencia {
    String titulo;
    String descripcion;
    String contenido;
    Boolean multimedia;
    Categoria categoria;
    LocalDate fecha_carga_desde;
    LocalDate fecha_carga_hasta;
    Ubicacion ubicacion;
    LocalDate fecha_acontecimiento_desde;
    LocalDate fecha_acontecimiento_hasta;
    OrigenCarga origen_carga;

    public CriteriosDePertenencia(String titulo, String descripcion, String contenido, Boolean multimedia, Categoria categoria, LocalDate fecha_carga_desde, LocalDate fecha_carga_hasta, Ubicacion ubicacion, LocalDate fecha_acontecimiento_desde, LocalDate fecha_acontecimiento_hasta, OrigenCarga origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.multimedia = multimedia;
        this.categoria = categoria;
        this.fecha_carga_desde = fecha_carga_desde;
        this.fecha_carga_hasta = fecha_carga_hasta;
        this.ubicacion = ubicacion;
        this.fecha_acontecimiento_desde = fecha_acontecimiento_desde;
        this.fecha_acontecimiento_hasta = fecha_acontecimiento_hasta;
        this.origen_carga = origen_carga;
    }
}