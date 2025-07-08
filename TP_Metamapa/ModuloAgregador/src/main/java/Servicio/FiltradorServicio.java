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
        List<HechoDTO> hechosDTO = new ArrayList<>();
        hechosDTO = hechos.stream()
                .map(this::transformarHechoADTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTO transformarHechoADTO(Hecho hecho){
        HechoDTO hechoDTO = new HechoDTO();
        hechoDTO.setTitulo(hecho.getTitulo());
        hechoDTO.setDescripcion(hecho.getDescripcion());
        Categoria categoria = hecho.getCategoria();
        hechoDTO.setCategoria(categoria.getNombre());
        Contenido contenido = hecho.getContenido();
        hechoDTO.setContenido(contenido.getTexto());
        hechoDTO.setContenido_multimedia(hechoDTO.getContenido_multimedia());
        hechoDTO.setFechaAcontecimiento(hecho.getFecha());
        hechoDTO.setFechaCarga(hecho.getFecha_carga());
        hechoDTO.setLugar(hecho.getUbicacion().getNombre());
        hechoDTO.setLatitud(hecho.getUbicacion().getLatitud());
        hechoDTO.setLongitud(hecho.getUbicacion().getLongitud());
        if (hecho.isAnonimo()) {
            hechoDTO.setUsuario(hecho.getUsuario());
            hechoDTO.setNombre(ContribuyenteRepositorio.obtenerPorId(hecho.getUsuario()).getNombre());
            hechoDTO.setApellido(ContribuyenteRepositorio.obtenerPorId(hecho.getUsuario()).getApellido());
            hechoDTO.setFecha_nacimiento(ContribuyenteRepositorio.obtenerPorId(hecho.getUsuario()).getFecha_nacimiento());
        }
        hechoDTO.setOrigen_carga(hecho.getOrigen_carga().ordinal());
        return hechoDTO;
    }

}
