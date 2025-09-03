package Modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calle {
    String nombre_calle;
    Localidad localidad;

    public Calle(String nombre_calle, Localidad localidad) {
        this.nombre_calle = nombre_calle;
        this.localidad = localidad;
    }
}
