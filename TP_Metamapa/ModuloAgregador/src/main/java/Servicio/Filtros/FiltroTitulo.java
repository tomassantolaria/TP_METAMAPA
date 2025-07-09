package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

import org.springframework.stereotype.Service;

@Service
public class FiltroTitulo implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio){

        return criterio.getTitulo() == null || unHecho.getTitulo().equalsIgnoreCase(criterio.getTitulo());
    }
}