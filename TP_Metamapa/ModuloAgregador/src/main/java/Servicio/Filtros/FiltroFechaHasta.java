package Servicio.Filtros;

import Modelos.Entidades.Criterios;
import Modelos.Entidades.Hecho;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class FiltroFechaHasta implements Filtro {
    @Override
    public boolean cumple(Hecho unHecho, String fechaHasta){

        return fechaHasta == null || unHecho.getFecha_carga().isBefore(LocalDate.parse(fechaHasta)) || unHecho.getFecha().isBefore(LocalDate.parse(fechaHasta));
    }
}
