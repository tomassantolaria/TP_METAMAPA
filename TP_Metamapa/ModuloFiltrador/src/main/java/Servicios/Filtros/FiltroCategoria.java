package Servicios.Filtros;

import Modelos.CriteriosDTO;
import Modelos.HechoDTO;

import org.springframework.stereotype.Service;

@Service
public class FiltroCategoria implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio) {

        return criterio.getCategoria() == null || unHecho.getCategoria().equalsIgnoreCase(criterio.getCategoria());
    }
    public FiltroCategoria(){}
}
