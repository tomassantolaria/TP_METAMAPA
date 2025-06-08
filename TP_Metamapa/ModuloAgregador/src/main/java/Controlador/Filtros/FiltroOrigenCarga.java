package Controlador.Filtros;

import Controlador.Modelos.Entidades.Criterios;
import Controlador.Modelos.Entidades.Hecho;
import Controlador.Modelos.Entidades.OrigenCarga;

public class FiltroOrigenCarga implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        OrigenCarga criterio = criterios.getOrigen_carga();
        return criterio == null || unHecho.getOrigen_carga() == criterio;
    }
}
