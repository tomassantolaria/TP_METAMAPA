import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/normalizacion")
public class Controlador {
    @Autowired
    private Servicio servicio;

    @PostMapping ("/categorias" ) public void normalizarCategoria(String categoria) {
        String categoria_normalizada= servicio.normalizarCategoria(categoria);
        ResponseBody.status(200).body("Categoria normalizada: " + categoria_normalizada);
    }
}
