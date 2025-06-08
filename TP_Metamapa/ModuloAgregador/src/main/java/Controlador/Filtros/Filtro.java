package Controlador.Filtros;

import Controlador.Modelos.Entidades.Criterios;
import Controlador.Modelos.Entidades.Hecho;

public interface Filtro {
    boolean cumple(Hecho hecho, Criterios criterios);
}
