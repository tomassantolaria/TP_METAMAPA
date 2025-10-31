package Controlador;
import Modelos.DTOS.HechoDTO;
import Servicio.FuenteEstatica;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/fuenteEstatica")
public class EstaticaController {
    private final FuenteEstatica fuenteEstatica;

    @Autowired
    public EstaticaController(FuenteEstatica fuenteEstatica) {
        this.fuenteEstatica = fuenteEstatica;
    }

    @GetMapping("/hechos")
    public ResponseEntity<?> devolverHechos() {
        try {
            List<HechoDTO> hechos = new ArrayList<>();
            hechos = fuenteEstatica.getHechosNoEnviados();
            return ResponseEntity.ok(hechos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay hechos disponibles");
        }
    }
}