package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Hecho {

    private String titulo;
    private String descripcion;
    private String contenido;
    private String contenidoMultimedia;
    private String fecha;
    private String ubicacion;
    private String origen;

    public Hecho(String titulo, String descripcion, String contenido,
                 String contenidoMultimedia, String fecha, String ubicacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.contenidoMultimedia = contenidoMultimedia;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.origen = "Demo";
    }

}
