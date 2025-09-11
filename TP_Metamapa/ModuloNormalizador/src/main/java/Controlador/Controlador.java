package Controlador;

import Modelos.Categoria;
import Modelos.Ubicacion;
import Modelos.UbicacionDTO;
import Servicios.ServicioCategoria;
import Servicios.ServicioUbicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/normalizacion")
public class Controlador {
    @Autowired
    ServicioCategoria servicioCategoria;
    @Autowired
    ServicioUbicacion servicioUbicacion;

    // LAS CATEGORIAS Y UBICACIONES SON REQUEST BODY
    @PostMapping ("/categorias" ) public ResponseEntity<String> normalizarCategoria(@RequestBody String categoria) {
        Categoria categoria_normalizada= servicioCategoria.normalizarCategoria(categoria);
        return ResponseEntity.ok(categoria_normalizada.getNombre_categoria());
    }


    @PostMapping("/ubicaciones")
    public ResponseEntity<UbicacionDTO> normalizarUbicaciones(@RequestBody UbicacionDTO ubicacion) {
        Ubicacion u = servicioUbicacion.normalizarUbicacion(
                ubicacion.getPais(),
                ubicacion.getProvincia(),
                ubicacion.getLocalidad()
        );
        UbicacionDTO dto = new UbicacionDTO(u.getLocalidad().getNombre_localidad(), u.getProvincia().getNombre_provincia(), u.getPais().getNombre_pais());
        return ResponseEntity.ok(dto);
    }
    /*
    @PostMapping ("/fecha" ) public void normalizarFecha(String fecha) {
        LocalDate fechaNormalizada = servicio.normalizarFecha(fecha);
        ResponseBody.status(200).body("Fecha normalizada: " + fechaNormalizada);
    }*/


}
