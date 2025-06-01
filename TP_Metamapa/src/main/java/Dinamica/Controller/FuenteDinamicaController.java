package Dinamica.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


import Dinamica.Service.FuenteDinamicaService;

@RestController
@RequestMapping("/fuenteDinamica")
public class FuenteDinamicaController {

        private final FuenteDinamicaService fuenteDinamicaService;

        public FuenteDinamicaController(FuenteDinamicaService fuenteDinamicaService) {
            this.fuenteDinamicaService = fuenteDinamicaService;
        }

        @PostMapping("/hechos")
        public ResponseEntity<String> crearHecho(@RequestBody HechoContribuyenteDTO hechoContribuyenteDTO) {
            fuenteDinamicaService.crearHechoDTO(hechoContribuyenteDTO);
            return ResponseEntity.ok("Hecho creado exitosamente.");
        }


}
