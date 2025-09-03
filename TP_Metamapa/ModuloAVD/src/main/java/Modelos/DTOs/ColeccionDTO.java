package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ColeccionDTO {

    private  String titulo;
    private  String descripcion;
    private  CriterioDTO criterio;
    private  String origen_carga;

    public ColeccionDTO(String titulo, String descripcion, CriterioDTO criterio, String origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio = criterio;
        this.origen_carga = origen_carga;
    }
}
