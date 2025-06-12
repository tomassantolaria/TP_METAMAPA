package Servicio.Filtros;

import Modelos.Entidades.Hecho;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class FiltroFechaDesde implements Filtro{

    @Override
    public boolean cumple(Hecho unHecho, String fechaDesde) {

        return fechaDesde == null || unHecho.getFecha_carga().isAfter(LocalDate.parse(fechaDesde)) || unHecho.getFecha().isAfter(LocalDate.parse(fechaDesde));
    }
}