package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

import org.springframework.stereotype.Service;

@Service
public class FiltroUbicacion implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio){

        return criterio.getUbicacion() == null || unHecho.getUbicacion().getNombre().equalsIgnoreCase(criterio.getUbicacion().getNombre());
    }
}