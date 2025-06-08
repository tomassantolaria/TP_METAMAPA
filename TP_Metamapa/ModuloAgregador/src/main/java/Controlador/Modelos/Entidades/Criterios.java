package Controlador.Modelos.Entidades;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Criterios {
    String titulo;
    String descripcion;
    Contenido contenido;
    Categoria categoria;
    LocalDate fecha;
    Ubicacion ubicacion;
    LocalDate fecha_carga;
    OrigenCarga origen_carga;

    public Criterios(String titulo, String descripcion, Contenido contenido, Categoria categoria, LocalDate fecha, Ubicacion ubicacion, LocalDate fecha_carga, OrigenCarga origen_carga) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.categoria = categoria;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.fecha_carga = fecha_carga;
        this.origen_carga = origen_carga;
    }
}