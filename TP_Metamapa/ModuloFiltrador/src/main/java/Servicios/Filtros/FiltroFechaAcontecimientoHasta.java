package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;
import java.time.LocalDate;

public class FiltroFechaAcontecimientoHasta implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getFechaHechoHasta() == null || unHecho.getFechaAcontecimiento().isBefore(LocalDate.parse(criterio.getFechaHechoHasta()));
    }
}
