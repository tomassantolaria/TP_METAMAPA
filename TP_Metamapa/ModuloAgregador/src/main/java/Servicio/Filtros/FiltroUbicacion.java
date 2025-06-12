package Servicio.Filtros;

import Modelos.Entidades.Criterios;
import Modelos.Entidades.Hecho;

import org.springframework.stereotype.Service;

@Service
public class FiltroUbicacion implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, String ubicacion){

        return ubicacion == null || unHecho.getUbicacion().getNombre().equalsIgnoreCase(ubicacion);
    }
}