package Controlador;

import Servicio.AgregadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController
@RequestMapping("agregador")
public class AgregadorController {
    @Autowired
    AgregadorServicio agregadorServicio;

    @PostMapping("/coleccionCreada/{id}")
    public ResponseEntity<String> coleccionCreada(@PathVariable Long id) {
        try {
            agregadorServicio.cargarColeccionConHechos(id);
            return ResponseEntity.status(200).body("Hechos agregados a la coleccion");
        } catch ( Exception e ) {
            return ResponseEntity.status(500).body("Los hechos no fueron agregados a la coleccion" + e.getMessage());
        }

    }

}
