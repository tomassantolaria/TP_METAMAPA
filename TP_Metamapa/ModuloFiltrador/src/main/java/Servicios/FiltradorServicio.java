package Servicios;

import Modelos.CriteriosDTO;

import Modelos.HechoDTO;
import org.springframework.stereotype.Service;
import Servicios.Filtros.*;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiltradorServicio {

    FiltroCategoria filtroPorCategoria;
    FiltroFechaCargaDesde filtroPorFechaCargaDesde;
    FiltroFechaCargaHasta filtroPorFechaCargaHasta;
    FiltroContenidoMultimedia filtroContenidoMultimedia;
    FiltroTitulo filtroPorTitulo;
    FiltroUbicacion filtroPorUbicacion;
    FiltroOrigenCarga filtroPorOrigenCarga;
    FiltroFechaAcontecimientoDesde filtroPorFechaAcontecimientoDesde;
    FiltroFechaAcontecimientoHasta filtroPorFechaAcontecimientoHasta;



    public List<HechoDTO> filtrarHechos(List<HechoDTO> hechosDTO, CriteriosDTO criteriosDTO) {
        return this.hechosCumplenCriterios(hechosDTO, criteriosDTO);
    }

    public List<HechoDTO> hechosCumplenCriterios(List<HechoDTO> hechos, CriteriosDTO criterios) {
        return hechos.stream()
                .filter(h -> filtroPorCategoria.cumple(h, criterios))
                .filter(h -> filtroContenidoMultimedia.cumple(h, criterios))
                .filter(h -> filtroPorFechaCargaDesde.cumple(h, criterios))
                .filter(h -> filtroPorFechaCargaHasta.cumple(h, criterios))
                .filter(h -> filtroPorTitulo.cumple(h, criterios))
                .filter(h -> filtroPorUbicacion.cumple(h, criterios))
                .filter(h -> filtroPorOrigenCarga.cumple(h, criterios))
                .filter(h -> filtroPorFechaCargaDesde.cumple(h, criterios))
                .filter(h -> filtroPorFechaCargaHasta.cumple(h, criterios))
                .filter(h -> filtroPorFechaAcontecimientoDesde.cumple(h, criterios))
                .filter(h -> filtroPorFechaAcontecimientoHasta.cumple(h, criterios))
                .collect(Collectors.toList());
    }


}
