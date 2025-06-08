package Controlador.Filtros;

import Controlador.Modelos.Entidades.Criterios;
import Controlador.Modelos.Entidades.Hecho;

import java.time.LocalDate;

public class FiltroFechaCarga implements Filtro {
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        LocalDate criterio = criterios.getFecha_carga();
        return criterio == null || criterio == unHecho.getFecha_carga();
    }
}
