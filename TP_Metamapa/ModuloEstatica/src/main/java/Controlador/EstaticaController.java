package Controlador;

import Modelos.HechoDTO;
import Modelos.HechoCSV;
import Servicio.FuenteEstatica;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fuenteEstatica")
public class EstaticaController {
    private final FuenteEstatica fuenteEstatica;

    public EstaticaController(FuenteEstatica fuenteEstatica) {
        this.fuenteEstatica = fuenteEstatica;
    }

    @GetMapping("/hechos")
    public Object devolverHechos() {
        return fuenteEstatica.getHechos();
    }

}







//public class FuenteDinamicaController {
//

//
//    @PostMapping("/hechos")
//    public ResponseEntity<String> crearHecho(@RequestBody HechoDTOInput hechoDTO) {
//        fuenteDinamicaService.crearHecho(hechoDTO);
//        return ResponseEntity.ok("Hecho creado exitosamente.");
//    }
//
//
//}