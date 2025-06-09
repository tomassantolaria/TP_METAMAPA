package Controlador;

import Controlador.Modelos.DTOs.ContribuyenteDTOInput;
import Servicio.RegistroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroControlador {
    @Autowired
    private RegistroServicio registroServicio;

    @PostMapping("/registrarse")
    public ResponseEntity<String> registrarse(@RequestBody ContribuyenteDTOInput contribuyente){
        registroServicio.registrar(contribuyente);
        return ResponseEntity.ok("Registro registrado exitosamente");
    }
}
