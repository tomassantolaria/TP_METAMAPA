package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;
import org.springframework.stereotype.Service;

@Service
public class FiltroContenidoMultimedia implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio) {

        return criterio.getContenidoMultimedia() == null || Boolean.parseBoolean(criterio.getContenidoMultimedia()) ;

    }
}