package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class FiltroFechaHasta implements Filtro {
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio){

        return criterio.getFecha_carga_hasta() == null || unHecho.getFecha_carga().isBefore(criterio.getFecha_carga_hasta()) || unHecho.getFecha().isBefore(criterio.getFecha_carga_hasta());
    }
}
