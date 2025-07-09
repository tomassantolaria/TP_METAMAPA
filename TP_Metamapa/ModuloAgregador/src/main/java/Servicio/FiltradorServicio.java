package Servicio;

import Modelos.Entidades.*;
import Modelos.DTOs.HechoDTO;
import Repositorio.ContribuyenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Servicio.Filtros.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiltradorServicio {

    @Autowired
    FiltroCategoria filtroPorCategoria;
    FiltroFechaDesde filtroPorFechaDesde;
    FiltroContenidoMultimedia filtroContenidoMultimedia;
    FiltroTitulo filtroPorTitulo;
    FiltroUbicacion filtroPorUbicacion;
    FiltroOrigenCarga filtroPorOrigenCarga;
    FiltroFechaHasta filtroPorFechaHasta;


    public List<HechoDTO> filtrarHechos(List<Hecho> hechos, String categoria, String contenidoMultimedia, String fechaCargaDesde, String fechaCargaHasta, String fechaHechoDesde, String fechaHechoHasta, String origen, String titulo, String ubicacion) {
        List<Hecho> hechos_filtrados = hechos.stream()
                .filter(h -> filtroPorCategoria.cumple(h, categoria))
                .filter(h -> filtroContenidoMultimedia.cumple(h, contenidoMultimedia))
                .filter(h -> filtroPorFechaDesde.cumple(h, fechaHechoDesde))
                .filter(h -> filtroPorFechaHasta.cumple(h, fechaHechoHasta))
                .filter(h -> filtroPorTitulo.cumple(h, titulo))
                .filter(h -> filtroPorUbicacion.cumple(h, ubicacion))
                .filter(h -> filtroPorOrigenCarga.cumple(h, origen))
                .filter(h -> filtroPorFechaDesde.cumple(h, fechaCargaDesde))
                .filter(h -> filtroPorFechaHasta.cumple(h, fechaCargaHasta))
                .collect(Collectors.toList());
        return this.transformarADTOLista(hechos_filtrados);
    }

    public List<HechoDTO> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTO> hechosDTO;
        hechosDTO = hechos.stream()
                .map(this::transformarAHechoDTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTO transformarAHechoDTO (Hecho hecho){
        HechoDTO hechoDTO = new HechoDTO(hecho.getTitulo(),hecho.getDescripcion(), hecho.getContenido().getTexto(),hecho.getContenido().getContenido_multimedia(),hecho.getCategoria().getNombre(), hecho.getFecha(), hecho.getFecha_carga(), hecho.getUbicacion().getNombre(), hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud(), hecho.getUsuario(), null, null, null, null, null, hecho.getOrigen_carga().name());
        if (hecho.isAnonimo()) {
            hechoDTO.setUsuario(null);
        }
        return hechoDTO;
    }
}
