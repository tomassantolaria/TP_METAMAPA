package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolicitudDTO {
    private Long idSolicitud;
    private String motivo;
    private HechoDTO hecho;
    private LocalDateTime fechaCreacion;

    public SolicitudDTO(Long idSolicitud, String motivo, HechoDTO hecho, LocalDateTime fechaCreacion){
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.hecho = hecho;
        this.fechaCreacion = fechaCreacion;
    }

    public SolicitudDTO(){}
}
