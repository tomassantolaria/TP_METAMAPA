package Controlador;


import Modelos.DTOs.FuentesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import servicios.FuentesServicio;

import java.util.List;

@RestController
public class FuentesController {

    @Autowired
    FuentesServicio fuentesServicio;

    @GetMapping("/fuentes")
    public ResponseEntity<List<FuentesDTO>> listaDeFuentes(){
        List<FuentesDTO> fuentes = fuentesServicio.obtenerFuentes();
        return ResponseEntity.ok(fuentes);
    }

    @PostMapping("/fuentes")
    public ResponseEntity<?> crearFuente(@RequestBody FuentesDTO fuente){
        fuentesServicio.crearFuente(fuente);
        System.out.println("Nueva fuente recibida: URL=" + fuente.getUrl() + ", Tipo=" + fuente.getTipoFuente());
        return ResponseEntity.ok().build();
    }
}
