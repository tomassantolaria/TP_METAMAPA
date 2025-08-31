package Controlador;

import Modelos.HechoDTO;
import Servicios.HechoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/dinamica")
public class HechoControlador {

    @Autowired
    HechoServicio hechoServicio;

    @PostMapping("/hechos")
    public ResponseEntity<String> crearHecho(@RequestBody HechoDTO hechoDTO) {
        hechoServicio.crearHecho(hechoDTO);
        return ResponseEntity.ok("Hecho creado exitosamente.");
    }

    @RequestMapping("/hechos")
    public List<HechoDTO> obtenerHechos() {
        return hechoServicio.obtenerHechos();
    }
}