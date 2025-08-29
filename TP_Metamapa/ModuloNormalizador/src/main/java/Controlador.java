import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/normalizacion")
public class Controlador {
    @Autowired
    private Servicio servicio;

    @PostMapping ("/categorias" ) public void normalizarCategoria(String categoria) {
        String categoria_normalizada= servicio.normalizarCategoria(categoria);
        ResponseBody.status(200).body("Categoria normalizada: " + categoria_normalizada);
    }

    @PostMapping ("/fecha" ) public void normalizarFecha(String fecha) {
        LocalDate fechaNormalizada = servicio.normalizarFecha(fecha);
        ResponseBody.status(200).body("Fecha normalizada: " + fechaNormalizada);
    }

}
