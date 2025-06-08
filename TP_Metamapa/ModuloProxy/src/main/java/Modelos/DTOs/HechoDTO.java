package Modelos.DTOs;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class HechoDTO {
    private String titulo;
    private String descripcion;
    private String contenido;
    private String contenidoMultimedia;
    private String categoria;
    private String ubicacion;
    private LocalDate fechaReporte;
    private LocalDate fechaAcontecimiento;
}