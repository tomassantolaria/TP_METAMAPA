package Servicio;

import Modelos.Entidades.*;
import Modelos.DTOs.HechoDTOOutput;
import Modelos.Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Servicio.Filtros.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public List<HechoDTOOutput> filtrarHechos(List<Hecho> hechos, String categoria, String contenidoMultimedia, String fechaCargaDesde, String fechaCargaHasta, String fechaHechoDesde, String fechaHechoHasta, String origen, String titulo, String ubicacion) {
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

    public List<HechoDTOOutput> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTOOutput> hechosDTO = new ArrayList<>();
        hechosDTO = hechos.stream()
                .map(this::transformarHechoADTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTOOutput transformarHechoADTO(Hecho hecho){
        HechoDTOOutput hechoDTO = new HechoDTOOutput();
        hechoDTO.setTitulo(hecho.getTitulo());
        hechoDTO.setDescripcion(hecho.getDescripcion());
        Categoria categoria = hecho.getCategoria();
        hechoDTO.setCategoria(categoria.getNombre());
        Contenido contenido = hecho.getContenido();
        hechoDTO.setContenido(contenido.getTexto());
        hechoDTO.setContenido_multimedia(hechoDTO.getContenido_multimedia());
        LocalDate fecha = hecho.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaString = fecha.format(formatter);
        hechoDTO.setFecha(fechaString);
        Ubicacion ubicacion = hecho.getUbicacion();
        hechoDTO.setLugar(ubicacion.getNombre());
        return hechoDTO;
    }

}
