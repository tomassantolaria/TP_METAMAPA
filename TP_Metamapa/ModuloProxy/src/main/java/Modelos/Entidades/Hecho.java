package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Hecho {

    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenidoMultimedia;
    public String categoria;
    public LocalDate fecha;
    public String lugar;
    public Double latitud;
    public Double longitud;

        public Hecho(String titulo, String descripcion, String contenido,
                     String contenidoMultimedia, String categoria, LocalDate fecha, String lugar
                        , Double latitud, Double longitud) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.contenidoMultimedia = contenidoMultimedia;
        this.categoria = categoria;
        this.fecha = fecha;
        this.lugar = lugar;
        this.latitud = latitud;
        this.longitud = longitud;

        }

}

