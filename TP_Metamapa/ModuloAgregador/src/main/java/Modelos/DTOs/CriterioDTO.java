package Modelos.DTOs;


import Modelos.Entidades.Categoria;
import lombok.Getter;

import java.time.LocalDate;

@Getter

public class CriterioDTO {
    public String titulo;
    public String descripcion;
    public Boolean contenido_multimedia;
    public String categoria;
    public LocalDate fecha_carga_desde;
    public LocalDate fecha_carga_hasta;
    public String lugar;
    public LocalDate fecha_acontecimiento_desde;
    public LocalDate fecha_acontecimiento_hasta;
    public String origen_carga;
}
