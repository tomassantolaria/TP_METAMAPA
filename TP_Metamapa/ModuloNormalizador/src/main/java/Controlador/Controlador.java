package Controlador;

import Modelos.Categoria;
import Modelos.Ubicacion;
import Modelos.UbicacionDTO;
import Servicios.ServicioCategoria;
import Servicios.ServicioUbicacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/normalizacion")
public class Controlador {
 // PONER @AUTOWIRED
    private final ServicioCategoria servicioCategoria;
    private final ServicioUbicacion servicioUbicacion;

    public Controlador(ServicioCategoria servicioCategoria, ServicioUbicacion servicioUbicacion) {
        this.servicioCategoria = servicioCategoria;
        this.servicioUbicacion = servicioUbicacion;
    }
    // LAS CATEGORIAS Y UBICACIONES SON REQUEST BODY
    @PostMapping ("/categorias" ) public ResponseEntity<String> normalizarCategoria(@RequestBody String categoria) {
        Categoria categoria_normalizada= servicioCategoria.normalizarCategoria(categoria);
        return ResponseEntity.ok(categoria_normalizada.getNombre_categoria());
    }

    @PostMapping ("/ubicaciones") public ResponseEntity<Ubicacion> normalizarUbicaciones(@RequestBody UbicacionDTO ubicacion) {
        Ubicacion ubicacion_normalizada = servicioUbicacion.normalizarUbicacion(ubicacion.getPais(), ubicacion.getProvincia(), ubicacion.getLocalidad());
        return ResponseEntity.ok(ubicacion_normalizada);
    }
    /*
    @PostMapping ("/fecha" ) public void normalizarFecha(String fecha) {
        LocalDate fechaNormalizada = servicio.normalizarFecha(fecha);
        ResponseBody.status(200).body("Fecha normalizada: " + fechaNormalizada);
    }*/


}
