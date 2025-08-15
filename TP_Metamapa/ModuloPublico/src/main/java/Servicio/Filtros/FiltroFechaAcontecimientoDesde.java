package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;


public class FiltroFechaAcontecimientoDesde implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio) {

        return criterio.getFecha_acontecimiento_desde() == null || unHecho.getFecha().isAfter(criterio.getFecha_acontecimiento_desde());
    }
}
