package Modelos;

import lombok.Getter;

@Getter
public class Localidad {
    String nombre_localidad;
    Provincia provincia;

    public Localidad(String nombre_localidad, Provincia provincia) {
        this.nombre_localidad = nombre_localidad;
        this.provincia = provincia;
    }
}
