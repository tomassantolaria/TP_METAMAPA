package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class FiltroFechaDesde implements Filtro{

    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio) {

        return criterio.getFecha_carga_desde() == null || unHecho.getFecha_carga().isAfter(criterio.getFecha_carga_desde()) || unHecho.getFecha().isAfter(criterio.getFecha_carga_desde());
    }
}