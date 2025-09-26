package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ColeccionDTO {

    private  String titulo;
    private  String descripcion;
    private  CriterioDTO criterio;

    public ColeccionDTO(String titulo, String descripcion, CriterioDTO criterio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio = criterio;
    }

    public ColeccionDTO() {}
}
