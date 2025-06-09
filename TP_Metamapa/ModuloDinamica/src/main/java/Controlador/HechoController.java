package Controlador;

import Modelos.DTOs.HechoDTO;
import Servicio.HechoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/fuenteDinamica")
public class HechoController {

    @Autowired
    HechoService hechoService;

    @PostMapping("/hecho")
    public ResponseEntity<String> crearHecho(@RequestBody HechoDTO hechoDTO) {
        hechoService.crearHecho(hechoDTO);
        return ResponseEntity.ok("Hecho creado exitosamente.");
    }
}