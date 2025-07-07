package Modelos.DTOs;

import Modelos.Entidades.Categoria;
import Modelos.Entidades.Contenido;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
//PATRON DTO
public class HechoDTO {
    private String titulo;
    private String descripcion;
    private Contenido contenido;
    private Categoria categoria;
    private LocalDate fecha;
    private String lugar;
    private String usuario;
    private String anonimo;
}