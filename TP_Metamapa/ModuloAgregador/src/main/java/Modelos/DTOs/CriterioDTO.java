package Modelos.DTOs;


import Modelos.Entidades.Categoria;
import lombok.Getter;

import java.time.LocalDate;

@Getter

public class CriterioDTO {
    public String titulo;
    public String descripcion;
    public String contenido_texto;
    public String contenido_multimedia;
    public String categoria;
    public LocalDate fecha;
    public String lugar;
    public Double latitud;
    public Double longitud;
    public LocalDate fecha_carga;
    public Integer origen_carga;
}
