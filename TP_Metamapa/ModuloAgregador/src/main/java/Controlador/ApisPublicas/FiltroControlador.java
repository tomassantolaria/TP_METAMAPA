package Controlador.ApisPublicas;

import Modelos.DTOs.HechoDTO;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import Servicio.ConsensoServicio;
import Servicio.FiltradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("agregador/")

public class FiltroControlador {

    @Autowired
    FiltradorServicio FiltradorServicio;
    HechoRepositorio hechosRepositorio;
    ColeccionRepositorio coleccionRepositorio;
    ConsensoServicio consensoServicio;

    @RequestMapping("colecciones/{id}/hechos")
    public List<HechoDTO> coleccionFiltrada(@PathVariable UUID id
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
        // criterio de pertenencia
        return FiltradorServicio.filtrarHechos(coleccionRepositorio.obtenerPorId(id).getHechos(), categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origen, titulo, ubicacion);
    }

    @RequestMapping("hechos")
    public List<HechoDTO> hechosFiltrados(
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

    @RequestMapping("colecciones/{id}/curada")
    public List<HechoDTO> hechosConsensuados (@PathVariable UUID id){
        return consensoServicio.hechosConConsenso(id);
    }

    @RequestMapping("colecciones/{id}/irrestricta")
    public List<HechoDTO> hechosIrrestrictos(@PathVariable UUID id){
        return consensoServicio.hechosIrrestrictos(id);
    }


}