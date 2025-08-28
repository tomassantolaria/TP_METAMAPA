package Servicios.Filtros;

import Modelos.DTOs.CriteriosDTO;
import Modelos.DTOs.HechoDTO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class FiltroFechaCargaHasta implements Filtro {
    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio){

        return criterio.getFechaCargaHasta() == null || unHecho.getFechaCarga().isBefore(LocalDate.parse(criterio.getFechaCargaHasta()));
    }
}
