package Modelos.DTOs;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime ;
import java.time.LocalDateTime ;

@Getter
@Setter
public class CriterioDTO {
    public String titulo;
    public Boolean contenido_multimedia;
    public String categoria;
    public LocalDateTime  fecha_carga_desde;
    public LocalDateTime  fecha_carga_hasta;
    public String localidad;
    public String provincia;
    public String pais;
    public LocalDateTime  fecha_acontecimiento_desde;
    public LocalDateTime  fecha_acontecimiento_hasta;
    public String origen_carga;

    public CriterioDTO(String titulo, Boolean contenido_multimedia, String categoria, LocalDateTime  fecha_carga_desde, LocalDateTime  fecha_carga_hasta, String localidad, String provincia, String pais, LocalDateTime  fecha_acontecimiento_desde, LocalDateTime  fecha_acontecimiento_hasta, String origen_carga) {
        this.titulo = titulo;
        this.contenido_multimedia = contenido_multimedia;
        this.categoria = categoria;
        this.fecha_carga_desde = fecha_carga_desde;
        this.fecha_carga_hasta = fecha_carga_hasta;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.fecha_acontecimiento_desde = fecha_acontecimiento_desde;
        this.fecha_acontecimiento_hasta = fecha_acontecimiento_hasta;
        this.origen_carga = origen_carga;
    }

    public CriterioDTO(){}
}
