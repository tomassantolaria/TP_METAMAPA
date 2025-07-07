package Modelos.DTOs;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ColeccionDTO {
    private String titulo;
    private String descripcion;
    private CriterioDTO criterio_pertenencia;
    private UUID idFuente;
}
