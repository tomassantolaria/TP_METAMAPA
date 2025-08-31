package Modelos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class HechoDTOoutput {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public LocalDate fechaAcontecimiento;
    public LocalDate fechaCarga;
    public String calle;
    public String localidad;
    public String provincia;
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;
    public String origen_carga;

    public HechoDTOoutput(String titulo, String descripcion, String contenido, String contenido_multimedia, String categoria, LocalDate fechaAcontecimiento, LocalDate fechaCarga, String calle, String localidad, String provincia, String usuario, String nombre, String apellido, LocalDate fecha_nacimiento, String origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.contenido_multimedia = contenido_multimedia;
        this.categoria = categoria;
        this.fechaAcontecimiento = fechaAcontecimiento;
        this.fechaCarga = fechaCarga;
        this.calle = calle;
        this.localidad = localidad;
        this.provincia = provincia;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.origen_carga = origen_carga;
    }
}
