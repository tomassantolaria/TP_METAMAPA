package Servicios.Filtros;

import Modelos.CriteriosDTO;
import Modelos.HechoDTO;
import java.time.LocalDate;

public class FiltroFechaAcontecimientoHasta implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getFechaHechoHasta() == null || unHecho.getFechaAcontecimiento().isBefore(LocalDate.parse(criterio.getFechaHechoHasta()));
    }
}
