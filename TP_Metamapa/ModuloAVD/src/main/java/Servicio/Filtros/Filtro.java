package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

public interface Filtro {
    boolean cumple(Hecho hecho, CriteriosDePertenencia criterio);
}
