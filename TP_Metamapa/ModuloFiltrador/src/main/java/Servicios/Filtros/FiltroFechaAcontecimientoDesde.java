package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;
import java.time.LocalDate;


public class FiltroFechaAcontecimientoDesde implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio) {

        return criterio.getFechaHechoDesde() == null || unHecho.getFechaAcontecimiento().isAfter(LocalDate.parse(criterio.getFechaHechoDesde()));
    }
}
