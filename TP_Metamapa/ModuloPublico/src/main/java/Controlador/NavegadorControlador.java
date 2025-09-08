package Controlador;

import Modelos.HechoDTO;
import Servicio.ConsensoServicio;
import Servicio.NavegadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("publico/")

public class NavegadorControlador {

    @Autowired
    NavegadorServicio navegadorServicio;
    @Autowired
    ConsensoServicio consensoServicio;

    @GetMapping("colecciones/{id}/hechos")
    public List<HechoDTO> coleccionFiltrada(@PathVariable Long id
            , @RequestParam (required = false) String categoria
            , @RequestParam (required = false) Boolean contenidoMultimedia
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCargaDesde
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaCargaHasta
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaHechoDesde
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaHechoHasta
            , @RequestParam (required = false) String origen
            , @RequestParam (required = false) String titulo
            , @RequestParam (required = false) String pais
            , @RequestParam (required = false) String provincia
            , @RequestParam (required = false) String localidad)

    {
        return navegadorServicio.filtrarHechos(id, categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origen, titulo, pais, provincia, localidad);
    }

    @GetMapping("hechos")
    public List<HechoDTO> hechosFiltrados(
              @RequestParam (required = false) String categoria
            , @RequestParam (required = false) Boolean contenidoMultimedia
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCargaDesde
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaCargaHasta
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaHechoDesde
            , @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  fechaHechoHasta
            , @RequestParam (required = false) String origen
            , @RequestParam (required = false) String titulo
            , @RequestParam (required = false) String pais
            , @RequestParam (required = false) String provincia
            , @RequestParam (required = false) String localidad)
    {
            return navegadorServicio.filtrarHechos(null,categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origen, titulo, pais, provincia, localidad);
    }

    @GetMapping("colecciones/{id}/curada")
    public List<HechoDTO> hechosConsensuados (@PathVariable Long id){
        return consensoServicio.hechosConConsenso(id);
    }

    @GetMapping("colecciones/{id}/irrestricta")
    public List<HechoDTO> hechosIrrestrictos(@PathVariable Long id){
        return consensoServicio.hechosIrrestrictos(id);
    }


}