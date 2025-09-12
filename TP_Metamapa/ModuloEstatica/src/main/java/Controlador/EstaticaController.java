package Controlador;
import Modelos.DTOS.HechoDTO;
import Servicio.FuenteEstatica;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("fuenteEstatica")
public class EstaticaController {
    private final FuenteEstatica fuenteEstatica;

    public EstaticaController(FuenteEstatica fuenteEstatica) {
        this.fuenteEstatica = fuenteEstatica;
    }

    @GetMapping("/hechos")
    public ResponseEntity<?> devolverHechos() {
        List<HechoDTO> hechos = new ArrayList<>();
        hechos = fuenteEstatica.getHechosNoEnviados();
        if (hechos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay hechos disponibles");
        }
        return ResponseEntity.ok(hechos);
    }

}







