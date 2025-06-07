package Controlador;

import Controlador.Colecciones.Coleccion;
import lombok.Getter;

@Getter
public class Busqueda extends Criterios {
    Coleccion coleccion;
    public Coleccion getColeccion() { return coleccion; }
}
