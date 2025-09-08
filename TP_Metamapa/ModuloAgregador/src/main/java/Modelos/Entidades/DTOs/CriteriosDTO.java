package Modelos.Entidades.DTOs;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class CriteriosDTO {
    String categoria;
    String contenidoMultimedia;
    String fechaCargaDesde;
    String fechaCargaHasta;
    String fechaHechoDesde;
    String fechaHechoHasta;
    String origen;
    String titulo;
    String localidad;
    String provincia;
    String pais;

    public CriteriosDTO(String categoria, String contenidoMultimedia, String fechaCargaDesde, String fechaCargaHasta, String fechaHechoDesde, String fechaHechoHasta, String origen, String titulo, String localidad, String provincia, String pais) {
        this.categoria = categoria;
        this.contenidoMultimedia = contenidoMultimedia;
        this.fechaCargaDesde = fechaCargaDesde;
        this.fechaCargaHasta = fechaCargaHasta;
        this.fechaHechoDesde = fechaHechoDesde;
        this.fechaHechoHasta = fechaHechoHasta;
        this.origen = origen;
        this.titulo = titulo;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
    }
}
