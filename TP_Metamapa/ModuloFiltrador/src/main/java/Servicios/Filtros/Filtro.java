package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;


public interface Filtro {
    boolean cumple(HechoDTO hecho, CriteriosDTO criterio);
}
