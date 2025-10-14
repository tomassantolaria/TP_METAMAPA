package Controlador;

import Modelos.DTOs.HechoDTO;

import servicios.FuenteMetaMapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metamapa")
public class MetaMapaControler {

    @Autowired
    FuenteMetaMapaService metaMapaServicio;


    @GetMapping("/hechos")
    public List<HechoDTO> obtenerHechos(
            /*@RequestParam(required = false) String categoria,
            @RequestParam (required = false) String ContenidoMultimedia,
            @RequestParam(required = false) String fecha_reporte_desde,
            @RequestParam(required = false) String fecha_reporte_hasta,
            @RequestParam(required = false) String fecha_acontecimiento_desde,
            @RequestParam(required = false) String fecha_acontecimiento_hasta,
            @RequestParam (required = false) String origen,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String provincia,
            @RequestParam(required = false) String localidad*/) {
        return metaMapaServicio.obtenerHechos(/*categoria, ContenidoMultimedia,
                fecha_reporte_desde,
                fecha_reporte_hasta,
                fecha_acontecimiento_desde,
                fecha_acontecimiento_hasta,
                origen,
                titulo,
                pais,
                provincia,
                localidad*/);
    }

    /*@GetMapping("/colecciones/{idColeccion}/hechos")
    public List<HechoDTO> obtenerHechosPorColeccion(
            @PathVariable String idColeccion,
            @RequestParam(required = false) String categoria,
            @RequestParam (required = false) String ContenidoMultimedia,
            @RequestParam(required = false) String fecha_reporte_desde,
            @RequestParam(required = false) String fecha_reporte_hasta,
            @RequestParam(required = false) String fecha_acontecimiento_desde,
            @RequestParam(required = false) String fecha_acontecimiento_hasta,
            @RequestParam (required = false) String origen,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String ubicacion) {
        return metaMapaServicio.obtenerHechosPorColeccion(idColeccion,
                categoria, ContenidoMultimedia,
                fecha_reporte_desde,
                fecha_reporte_hasta,
                fecha_acontecimiento_desde,
                fecha_acontecimiento_hasta,
                origen,
                titulo,
                ubicacion);
    }

    @PostMapping("/solicitudes")
    public ResponseEntity<String> recibirSolicitud(@RequestBody SolicitudDTO solicitud) {
        try {
            metaMapaServicio.crearSolicitud(solicitud);
            return ResponseEntity.ok("Solicitud creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la solicitud: " + e.getMessage());
        }
    }*/

}