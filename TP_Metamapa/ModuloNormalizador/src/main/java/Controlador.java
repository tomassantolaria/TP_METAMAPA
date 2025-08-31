import Modelos.Categoria;
import Modelos.Ubicacion;
import Servicios.ServicioCategoria;
import Servicios.ServicioUbicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/normalizacion")
public class Controlador {
    @Autowired
    private ServicioCategoria servicioCategoria;
    private ServicioUbicacion servicioUbicacion;

    @PostMapping ("/categorias" ) public void normalizarCategoria(String categoria) {
        Categoria categoria_normalizada= servicioCategoria.normalizarCategoria(categoria);
        ResponseEntity.status(200).body("Categoria normalizada: " + categoria_normalizada.getNombre_categoria());
    }

    @PostMapping ("/ubicaciones") public void normalizarUbicaciones(String provincia, String calle, String localidad) {
        Ubicacion ubicacion_normalizada = servicioUbicacion.normalizarUbicacion(provincia, calle, localidad);
        ResponseEntity.status(200).body("Ubicación normalizada: " + ubicacion_normalizada);
    }
    /*
    @PostMapping ("/fecha" ) public void normalizarFecha(String fecha) {
        LocalDate fechaNormalizada = servicio.normalizarFecha(fecha);
        ResponseBody.status(200).body("Fecha normalizada: " + fechaNormalizada);
    }*/


}
