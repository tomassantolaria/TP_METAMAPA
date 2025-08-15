package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;


import org.springframework.stereotype.Service;

@Service
public class FiltroOrigenCarga implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio){

        return criterio.getOrigen_carga() == null || unHecho.getOrigen_carga().toString().equalsIgnoreCase(criterio.getOrigen_carga().toString());
    }
}
