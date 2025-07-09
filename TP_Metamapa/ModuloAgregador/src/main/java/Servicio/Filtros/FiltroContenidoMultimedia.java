package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;

import org.springframework.stereotype.Service;

@Service
public class FiltroContenidoMultimedia implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio) {

        return criterio.getMultimedia() == null || criterio.getMultimedia();

    }
}