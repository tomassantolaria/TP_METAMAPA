package Controlador.Filtros;

import Controlador.Modelos.Entidades.Hecho;
import Controlador.Modelos.Entidades.Criterios;

public class FiltroTitulo implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        String criterio = criterios.getTitulo();
        return criterio == null || unHecho.getTitulo().contains(criterio);
    }
}