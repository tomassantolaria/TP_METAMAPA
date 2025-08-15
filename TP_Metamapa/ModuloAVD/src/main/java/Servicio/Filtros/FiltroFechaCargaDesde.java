package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

import org.springframework.stereotype.Service;

@Service
public class FiltroFechaCargaDesde implements Filtro{

    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio) {

        return criterio.getFecha_carga_desde() == null || unHecho.getFecha_carga().isAfter(criterio.getFecha_carga_desde());
    }
}