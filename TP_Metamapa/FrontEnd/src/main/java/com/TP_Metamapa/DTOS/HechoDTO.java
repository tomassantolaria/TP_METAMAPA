package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HechoDTO {
        public Long idHecho;
        public Long idFuente;//VER COMO MANEJAR ESTO
        public String titulo;
        public String descripcion;
        public String contenido;
        public String contenido_multimedia;
        public String categoria;
        public LocalDateTime fechaAcontecimiento;
        public LocalDateTime  fechaCarga;
        public String localidad;
        public String provincia;
        public String pais;
        public Double latitud;
        public Double longitud;
        public String usuario;
        public Boolean anonimo;
        public Boolean visible;
        public String origen_carga;
}
