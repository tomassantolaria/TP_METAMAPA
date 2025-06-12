package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ubicacion {
    private String nombre;
    private String latitud;
    private String longitud;

    public Ubicacion(String nombre, String latitud, String longitud) {}
}
