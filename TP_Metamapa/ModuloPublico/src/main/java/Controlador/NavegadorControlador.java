package Controlador;

import Modelos.HechoDTO;
import Servicio.ConsensoServicio;
import Servicio.NavegadorServicio;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("publico/")

public class NavegadorControlador {


    NavegadorServicio navegadorServicio;
    ConsensoServicio consensoServicio;

    @RequestMapping("colecciones/{id}/hechos")
    public List<HechoDTO> coleccionFiltrada(@PathVariable Long id
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
        return navegadorServicio.filtrarHechos(id, categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, titulo, ubicacion, origen);
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
            return navegadorServicio.filtrarHechos(null,categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, titulo, ubicacion, origen);
    }

    @RequestMapping("colecciones/{id}/curada")
    public List<HechoDTO> hechosConsensuados (@PathVariable Long id){
        return consensoServicio.hechosConConsenso(id);
    }

    @RequestMapping("colecciones/{id}/irrestricta")
    public List<HechoDTO> hechosIrrestrictos(@PathVariable Long id){
        return consensoServicio.hechosIrrestrictos(id);
    }


}