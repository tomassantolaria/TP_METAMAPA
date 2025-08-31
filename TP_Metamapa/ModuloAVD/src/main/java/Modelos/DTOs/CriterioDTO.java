package Modelos.DTOs;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CriterioDTO {
    public String titulo;
    public String descripcion;
    public Boolean contenido_multimedia;
    public String categoria;
    public LocalDate fecha_carga_desde;
    public LocalDate fecha_carga_hasta;
    public String calle;
    public String localidad;
    public String provincia;
    public LocalDate fecha_acontecimiento_desde;
    public LocalDate fecha_acontecimiento_hasta;
    public String origen_carga;

    public CriterioDTO(String titulo, String descripcion, Boolean contenido_multimedia, String categoria, LocalDate fecha_carga_desde, LocalDate fecha_carga_hasta, String calle, String localidad, String provincia, LocalDate fecha_acontecimiento_desde, LocalDate fecha_acontecimiento_hasta, String origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido_multimedia = contenido_multimedia;
        this.categoria = categoria;
        this.fecha_carga_desde = fecha_carga_desde;
        this.fecha_carga_hasta = fecha_carga_hasta;
        this.calle = calle;
        this.localidad = localidad;
        this.provincia = provincia;
        this.fecha_acontecimiento_desde = fecha_acontecimiento_desde;
        this.fecha_acontecimiento_hasta = fecha_acontecimiento_hasta;
        this.origen_carga = origen_carga;
    }
}
