package com.TP_Metamapa.DTOS;

import lombok.Data;

@Data
public class ColeccionDTOInput {
    private  String titulo;
    private  String descripcion;
    private  CriterioDTO criterio;
    private  String consenso;

    public ColeccionDTOInput(String titulo, String descripcion, CriterioDTO criterio, String consenso) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio = criterio;
        this.consenso = consenso;
    }

    public ColeccionDTOInput() {}
}
