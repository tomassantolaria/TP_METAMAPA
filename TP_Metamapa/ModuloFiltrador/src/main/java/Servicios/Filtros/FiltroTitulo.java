package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;
import org.springframework.stereotype.Service;

@Service
public class FiltroTitulo implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getTitulo() == null || unHecho.getTitulo().equalsIgnoreCase(criterio.getTitulo());
    }
}