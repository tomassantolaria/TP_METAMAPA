package Servicio.Filtros;

import Modelos.Entidades.Hecho;
import Modelos.Entidades.Criterios;

import org.springframework.stereotype.Service;

@Service
public class FiltroTitulo implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, String titulo){

        return titulo == null || unHecho.getTitulo().equalsIgnoreCase(titulo);
    }
}