package Controlador;

import Modelos.ContribuyenteDTO;
import servicios.RegistroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroControlador {

    @Autowired
    private final RegistroServicio registroServicio;
    public RegistroControlador(RegistroServicio registroServicio) {
        this.registroServicio = registroServicio;
    }

    @PostMapping("/registrarse")
    public ResponseEntity<String> registrarse(@RequestBody ContribuyenteDTO contribuyente){
        try{
            registroServicio.registrar(contribuyente);
            // Lógica para guardar Contribuyente usando registroDTO.getKeycloakUserId()
            // (Implica actualizar RegistroServicio y la Entidad Contribuyente para usar el KeycloakId como PK o campo clave)

            return ResponseEntity.status(200).body("Registro registrado exitosamente");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error al registrar contribuyente" + e.getMessage());
        }
    }


}