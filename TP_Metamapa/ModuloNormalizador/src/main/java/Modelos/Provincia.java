package Modelos;

import lombok.Getter;

@Getter
public class Provincia {
    String nombre_provincia;

    public Provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }
}
