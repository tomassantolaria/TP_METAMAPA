package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ColeccionDTO {

    private  String titulo;
    private  String descripcion;
    private  CriterioDTO criterio;
    private  String consenso;

    public ColeccionDTO(String titulo, String descripcion, CriterioDTO criterio, String consenso) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio = criterio;
        this.consenso = consenso;
    }

    public ColeccionDTO() {}
}
