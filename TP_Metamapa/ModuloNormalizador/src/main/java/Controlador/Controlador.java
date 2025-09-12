package Controlador;

import Modelos.Categoria;
import Modelos.Ubicacion;
import Modelos.UbicacionDTO;
import Servicios.ServicioCategoria;
import Servicios.ServicioTitulo;
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
    @Autowired
    private ServicioTitulo servicioTitulo;

    // LAS CATEGORIAS Y UBICACIONES SON REQUEST BODY
    @PostMapping ("/categorias" )
    public ResponseEntity<Categoria> normalizarCategoria(@RequestBody String categoria) {
        Categoria categoria_normalizada= servicioCategoria.normalizarCategoria(categoria);
        return ResponseEntity.ok(categoria_normalizada);
    }

    @PostMapping("/ubicaciones")
    public ResponseEntity<Ubicacion> normalizarUbicacion(@RequestBody UbicacionDTO ubicacion) {
        Ubicacion ubicacion_normalizada = servicioUbicacion.normalizarUbicacion(ubicacion.getLatitud(), ubicacion.getLongitud());
        return ResponseEntity.ok(ubicacion_normalizada);
    }

    @PostMapping("/titulos")
    public ResponseEntity<String> normalizarTitulo(@RequestBody String titulo) {
        String titulo_normalizado = servicioTitulo.normalizarTitulo(titulo);
        return ResponseEntity.ok(titulo_normalizado);
    }
}
