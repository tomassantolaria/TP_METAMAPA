package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;

import org.springframework.stereotype.Service;

@Service
public class FiltroCategoria implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio) {

        return criterio.getCategoria() == null || unHecho.getCategoria().equalsIgnoreCase(criterio.getCategoria());
    }
    public FiltroCategoria(){}
}
