package com.TP_Metamapa.DTOS;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ColeccionDTO {
    private  Long coleccionId;
    private  String titulo;
    private  String descripcion;
    private  CriterioDTO criterio;
    private  String consenso;

    public ColeccionDTO(Long coleccionId,String titulo, String descripcion, CriterioDTO criterio, String consenso) {
        this.coleccionId = coleccionId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio = criterio;
        this.consenso = consenso;
    }

    public ColeccionDTO() {}
}