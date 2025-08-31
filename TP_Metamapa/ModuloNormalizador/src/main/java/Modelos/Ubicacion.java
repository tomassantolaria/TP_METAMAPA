package Modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ubicacion {
    Calle calle;
    Localidad localidad;
    Provincia provincia;

    public void Ubicacion(Calle calle, Localidad localidad, Provincia provincia) {
        this.calle = calle;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
