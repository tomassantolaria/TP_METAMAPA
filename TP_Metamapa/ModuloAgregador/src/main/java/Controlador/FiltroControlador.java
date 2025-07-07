package Controlador;

import Modelos.DTOs.HechoDTOOutput;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import Servicio.FiltradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/Agregador")

public class FiltroControlador {

    @Autowired
    FiltradorServicio FiltradorServicio;
    HechoRepositorio hechosRepositorio;
    ColeccionRepositorio coleccionRepositorio;

    @RequestMapping("coleccion/{id}/filtros")
    public List<HechoDTOOutput> coleccionFiltrada(@PathVariable String id
            , @RequestParam (required = false) String categoria
            , @RequestParam (required = false) String contenidoMultimedia
            , @RequestParam (required = false) String fechaCargaDesde
            , @RequestParam (required = false) String fechaCargaHasta
            , @RequestParam (required = false) String fechaHechoDesde
            , @RequestParam (required = false) String fechaHechoHasta
            , @RequestParam (required = false) String origen
            , @RequestParam (required = false) String titulo
            , @RequestParam (required = false) String ubicacion)
    {
        return FiltradorServicio.filtrarHechos(coleccionRepositorio.obtenerPorId(id).getHechos(), categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origen, titulo, ubicacion);
    }

    @RequestMapping("hechos/filtros")
    public List<HechoDTOOutput> hechosFiltrados(
            @RequestParam (required = false) String categoria
            , @RequestParam (required = false) String contenidoMultimedia
            , @RequestParam (required = false) String fechaCargaDesde
            , @RequestParam (required = false) String fechaCargaHasta
            , @RequestParam (required = false) String fechaHechoDesde
            , @RequestParam (required = false) String fechaHechoHasta
            , @RequestParam (required = false) String origen
            , @RequestParam (required = false) String titulo
            , @RequestParam (required = false) String ubicacion)
    {

            return FiltradorServicio.filtrarHechos(hechosRepositorio.getHechos(),categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origen, titulo, ubicacion);

    }

}