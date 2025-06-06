package Controlador;

import Modelos.DTOs.HechoDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import Servicio.HechoService;

@RestController
public class HechoController {

   private final HechoService hechoService;

    public HechoController(HechoService hechoService) {
        this.hechoService = hechoService;
    }

    @PostMapping("/hechos")
    public ResponseEntity<String> crearHecho(@RequestBody HechoDTO hechoDTO) {
        hechoService.crearHecho(hechoDTO);
        return ResponseEntity.ok("Hecho creado exitosamente.");
    }


}