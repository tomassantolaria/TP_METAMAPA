package Servicios.Filtros;

import Modelos.CriteriosDTO;
import Modelos.HechoDTO;
import org.springframework.stereotype.Service;

@Service
public class FiltroTitulo implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getTitulo() == null || unHecho.getTitulo().equalsIgnoreCase(criterio.getTitulo());
    }
}