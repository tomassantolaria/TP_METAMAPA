package Controlador;

import Servicios.impl.FuenteMetaMapaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metamapa")
public class MetaMapaControler {

    private final FuenteMetaMapaService metaMapaServicio;

    public MetaMapaControler(FuenteMetaMapaService metaMapaServicio) {
        this.metaMapaServicio = metaMapaServicio;
    }

    @GetMapping("/hechos")
    public Object obtenerHechos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String fecha_reporte_desde,
            @RequestParam(required = false) String fecha_reporte_hasta,
            @RequestParam(required = false) String fecha_acontecimiento_desde,
            @RequestParam(required = false) String fecha_acontecimiento_hasta,
            @RequestParam(required = false) String ubicacion) {
        return metaMapaServicio.obtenerHechos(categoria,
                fecha_reporte_desde,
                fecha_reporte_hasta,
                fecha_acontecimiento_desde,
                fecha_acontecimiento_hasta,
                ubicacion);
    }

    @GetMapping("/colecciones/{idColeccion}/hechos")
    public Object obtenerHechosPorColeccion(
            @PathVariable String idColeccion,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String fecha_reporte_desde,
            @RequestParam(required = false) String fecha_reporte_hasta,
            @RequestParam(required = false) String fecha_acontecimiento_desde,
            @RequestParam(required = false) String fecha_acontecimiento_hasta,
            @RequestParam(required = false) String ubicacion) {
        return metaMapaServicio.obtenerHechosPorColeccion(idColeccion,
                categoria,
                fecha_reporte_desde,
                fecha_reporte_hasta,
                fecha_acontecimiento_desde,
                fecha_acontecimiento_hasta,
                ubicacion);
    }

}
