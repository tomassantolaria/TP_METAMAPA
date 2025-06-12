package Modelos.DTOs;

import lombok.Getter;

@Getter
public class ColeccionDTO {
    private String titulo;
    private String descripcion;
    private CriterioDTO criterio_pertenencia;
    private String idFuente;

}
