package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ubicacion {
    private String nombre;
    private Double latitud;
    private Double longitud;

    public Ubicacion(String nombre, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
