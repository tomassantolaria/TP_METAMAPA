package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

public class FiltroFechaAcontecimientoHasta implements Filtro {
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio){

        return criterio.getFecha_acontecimiento_hasta() == null || unHecho.getFecha().isBefore(criterio.getFecha_carga_hasta());
    }
}
