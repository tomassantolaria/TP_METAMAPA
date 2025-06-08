package Controlador.Modelos.DTOs;

import Controlador.Modelos.Entidades.CriterioDePertenencia;
import lombok.Getter;

import java.util.List;
@Getter
public class ColeccionDTO {
    private String titulo;
    private String descripcion;
    private CriterioDTO criterio_pertenencia;
    private String idFuente;

}
