package Servicio.Filtros;

import Modelos.Entidades.Criterios;
import Modelos.Entidades.Hecho;
import Modelos.Entidades.OrigenCarga;


import org.springframework.stereotype.Service;

@Service
public class FiltroOrigenCarga implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, String origen){

        return origen == null || unHecho.getOrigen_carga().toString().equalsIgnoreCase(origen);
    }
}
