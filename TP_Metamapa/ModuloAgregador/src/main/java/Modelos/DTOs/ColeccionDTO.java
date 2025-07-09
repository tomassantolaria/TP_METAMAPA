package Modelos.DTOs;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ColeccionDTO {
    private String titulo;
    private String descripcion;
    private String contenido;
    private Boolean multimedia;
    private String categoria;
    private LocalDate fecha_carga_desde;
    private LocalDate fecha_carga_hasta;
    private String lugar;
    private LocalDate fecha_acontecimiento_desde;
    private LocalDate fecha_acontecimiento_hasta;
    private String origen_carga;
}
