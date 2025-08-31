package Servicios.Filtros;

import Modelos.CriteriosDTO;
import Modelos.HechoDTO;
import org.springframework.stereotype.Service;

@Service
public class FiltroUbicacion implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getUbicacion() == null || unHecho.getLugar().equalsIgnoreCase(criterio.getUbicacion());
    }
}