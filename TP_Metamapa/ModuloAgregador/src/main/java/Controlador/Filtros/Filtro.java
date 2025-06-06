package Controlador.Filtros;

import Controlador.Criterios;
import Controlador.Hecho;

public interface Filtro {
    boolean cumple(Hecho hecho, Criterios criterios);
}
