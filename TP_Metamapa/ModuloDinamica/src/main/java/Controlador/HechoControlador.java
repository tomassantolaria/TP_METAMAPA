package Controlador;


import Modelos.HechoDTO;
import Modelos.HechoDTOInput;
import servicios.HechoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            hechoServicio.crearHecho(hechoDTO);
            return ResponseEntity.ok("Hecho creado exitosamente.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el hecho");
        }
    }

    @GetMapping("/hechos")
    public List<HechoDTO> obtenerHechos() {
        return hechoServicio.obtenerHechos();
    }
}