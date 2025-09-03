package Servicios.Filtros;

import Modelos.CriteriosDTO;
import Modelos.HechoDTO;


public interface Filtro {
    boolean cumple(HechoDTO hecho, CriteriosDTO criterio);
}
