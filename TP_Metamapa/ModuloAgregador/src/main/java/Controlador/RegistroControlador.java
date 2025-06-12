package Controlador;

import Modelos.DTOs.ContribuyenteDTOInput;
import Servicio.RegistroServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RegistroControlador {

    private final RegistroServicio registroServicio;
    public RegistroControlador(RegistroServicio registroServicio) {
        this.registroServicio = registroServicio;
    }

    @PostMapping("/registrarse")
    public ResponseEntity<String> registrarse(@RequestBody ContribuyenteDTOInput contribuyente){
        registroServicio.registrar(contribuyente);
        return ResponseEntity.ok("Registro registrado exitosamente");
    }
}
