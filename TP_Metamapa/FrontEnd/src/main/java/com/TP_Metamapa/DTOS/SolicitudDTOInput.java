package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolicitudDTOInput {


        private String motivo;
        private Long idHecho;


        public SolicitudDTOInput( String motivo, Long idHecho){
            this.motivo = motivo;
            this.idHecho = idHecho;

        }
    }

