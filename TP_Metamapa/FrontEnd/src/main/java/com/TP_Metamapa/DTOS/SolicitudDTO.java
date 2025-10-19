package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolicitudDTO {
    private Long idSolicitud;
    private String motivo;
    private Long idHecho;
    private LocalDateTime fechaCreacion;

    public SolicitudDTO(Long idSolicitud, String motivo, Long idHecho, LocalDateTime fechaCreacion){
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.idHecho = idHecho;
        this.fechaCreacion = fechaCreacion;
    }
}
