package com.TP_Metamapa.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class ColeccionDTO {
    private  Long coleccionId;
    private  String titulo;
    private  String descripcion;
    private  List<HechoDTO> hechos;
    private  CriterioDTO criterio;
    private  String consenso;
    private  List<HechoDTO> hechosConsensuados;

    public ColeccionDTO(Long coleccionId,String titulo, String descripcion, List<HechoDTO> hechos,CriterioDTO criterio, String consenso, List<HechoDTO> hechosConsensuados) {
        this.coleccionId = coleccionId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hechos = hechos;
        this.criterio = criterio;
        this.consenso = consenso;
        this.hechosConsensuados = hechosConsensuados;
    }

    public ColeccionDTO() {}
}