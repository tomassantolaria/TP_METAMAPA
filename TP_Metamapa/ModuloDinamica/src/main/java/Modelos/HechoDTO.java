package Modelos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//PATRON DTO
public class HechoDTO {
    public Long idHecho;
    public Long idFuente;
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public LocalDate fechaAcontecimiento;
    public LocalDate fechaCarga;
    public String nombre_localidad;
    public String nombre_provincia;
    public String nombre_pais;
    public Double latitud;
    public Double longitud;
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;
    public Boolean anonimo;
    public Boolean visible;
    public String origen_carga;

    public HechoDTO(Long idHecho, Long idFuente, String titulo, String descripcion, String contenido, String contenido_multimedia, String categoria, LocalDate fechaAcontecimiento, LocalDate fechaCarga, String nombre_localidad, String nombre_provincia, String nombre_pais, Double latitud, Double longitud, String usuario, String nombre, String apellido, LocalDate fecha_nacimiento, Boolean anonimo, Boolean visible, String origen_carga) {
        this.idHecho = idHecho;
        this.idFuente = idFuente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.contenido_multimedia = contenido_multimedia;
        this.categoria = categoria;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.fechaCarga = fechaCarga;
        this.nombre_localidad = nombre_localidad;
        this.nombre_provincia = nombre_provincia;
        this.nombre_pais = nombre_pais;
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
