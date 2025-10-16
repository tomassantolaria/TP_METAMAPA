package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class HechoDTO {
        public Long id;
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
        public String nombre;
        public String apellido;
        public LocalDateTime fecha_nacimiento;
        public Boolean anonimo;
        public Boolean visible;
        public String origen_carga;
}
