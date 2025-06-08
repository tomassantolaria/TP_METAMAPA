package Controlador.Filtros;

import Controlador.Modelos.Entidades.Criterios;
import Controlador.Modelos.Entidades.Hecho;
import Controlador.Modelos.Entidades.Ubicacion;

public class FiltroUbicacion implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        String criterio = criterios.getUbicacion().getNombre();
        return criterio == null || unHecho.getUbicacion().getNombre() == criterio;
    }
}