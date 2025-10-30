package Controlador;

import Modelos.RegistroRequestDTO;
import Modelos.RegistroResponseDTO;
import Servicios.KeycloakUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    private final KeycloakUserService keycloakUserService;

    public RegistroController(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PostMapping("/registro/contribuyente")
    public ResponseEntity<RegistroResponseDTO> registrarContribuyente(@RequestBody RegistroRequestDTO registroDTO) {

        // La validación de datos (ej. email único) debería hacerse aquí o en el servicio.
        if (registroDTO.getPassword() == null || registroDTO.getUsuario() == null) {
            return ResponseEntity.badRequest().body(new RegistroResponseDTO(null, "Faltan campos obligatorios (usuario, email, password).", 400));
        }

        RegistroResponseDTO response = keycloakUserService.registrarContribuyente(registroDTO);

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}