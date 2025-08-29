package Modelos.DTOs;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//PATRON DTO
public class HechoDTO {
    public Long idHecho = null;
    public Long idFuente = null; //VER COMO MANEJAR ESTO
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public LocalDate fechaAcontecimiento;
    public LocalDate fechaCarga;
    public String lugar;
    public Double latitud;
    public Double longitud;
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;
    public Boolean anonimo;
    public Boolean visible;
    public String origen_carga;

    public HechoDTO(String titulo, String descripcion, String contenido, String contenido_multimedia, String categoria, LocalDate fechaAcontecimiento, LocalDate fechaCarga, String lugar, Double latitud, Double longitud, String usuario, String nombre, String apellido, LocalDate fecha_nacimiento, Boolean anonimo, Boolean visible, String origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.contenido_multimedia = contenido_multimedia;
        this.categoria = categoria;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.fechaCarga = fechaCarga;
        this.lugar = lugar;
        this.latitud = latitud;
        this.longitud = longitud;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.anonimo = anonimo;
        this.visible = visible;
        this.origen_carga = origen_carga;
    }
}
