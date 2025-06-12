package Servicio.Filtros;

import Modelos.Entidades.Criterios;
import Modelos.Entidades.Hecho;

public interface Filtro {
    boolean cumple(Hecho hecho, String criterio);
}
