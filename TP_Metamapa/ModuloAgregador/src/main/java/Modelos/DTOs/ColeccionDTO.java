package Modelos.DTOs;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ColeccionDTO {
    private String titulo;
    private String descripcion;
    private CriterioDTO criterio;
    private String origen_carga;
}
