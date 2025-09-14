package Controlador;


import Modelos.HechoDTO;
import Modelos.HechoDTOInput;
import Servicios.HechoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/dinamica")
public class HechoControlador {

    @Autowired
     HechoServicio hechoServicio;

    @PostMapping("/hechos")
    public ResponseEntity<String> crearHecho(@RequestBody HechoDTOInput hechoDTO) {
        hechoServicio.crearHecho(hechoDTO);
        return ResponseEntity.ok("Hecho creado exitosamente.");
    }

    @GetMapping("/hechos")
    public List<HechoDTO> obtenerHechos() {
        return hechoServicio.obtenerHechos();
    }
}