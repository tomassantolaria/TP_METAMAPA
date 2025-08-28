package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;
import org.springframework.stereotype.Service;
@Service
public class FiltroOrigenCarga implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getOrigen() == null || unHecho.getOrigen_carga().equalsIgnoreCase(criterio.getOrigen());
    }
}
