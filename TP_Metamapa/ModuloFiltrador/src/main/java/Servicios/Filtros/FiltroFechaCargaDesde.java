package Servicios.Filtros;

import Modelos.CriteriosDTO;
import Modelos.HechoDTO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class FiltroFechaCargaDesde implements Filtro {

    @Override
    public boolean cumple(HechoDTO unHecho, CriteriosDTO criterio) {

        return criterio.getFechaCargaDesde() == null || unHecho.getFechaCarga().isAfter(LocalDate.parse(criterio.getFechaCargaDesde()));
    }
}