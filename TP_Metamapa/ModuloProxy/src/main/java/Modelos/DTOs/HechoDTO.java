package Modelos.DTOs;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HechoDTO {
    private Long idHecho;
    private Long idFuente;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String contenido_multimedia;
    private String categoria;
    private LocalDate fechaAcontecimiento;
    private LocalDate fechaCarga;
    private String localidad;
    private String provincia;
    private String pais;
    private Double latitud;
    private Double longitud;
    private String usuario;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private Boolean anonimo;
    private Boolean visible;
    private String origen_carga;

    public HechoDTO(String titulo, String descripcion, String contenido, String contenido_multimedia, String categoria,
        LocalDate fechaAcontecimiento, LocalDate fechaCarga, String localidad, String provincia, String pais,
        Double latitud, Double longitud, String usuario, String nombre, String apellido,
        LocalDate fecha_nacimiento, Boolean anonimo, Boolean visible, String origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.contenido_multimedia = contenido_multimedia;
        this.categoria = categoria;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.fechaCarga = fechaCarga;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
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

    public HechoDTO() {
        // constructor vacío para usar con Jackson/serialización
    }
}