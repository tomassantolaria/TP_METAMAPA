package Controlador;

import Modelos.DTOS.RegistroRequestDTO;
import Modelos.DTOS.RegistroResponseDTO;
import Servicios.KeyCloakUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    KeyCloakUserService keycloakUserService;


    @PostMapping("/contribuyente")
    public ResponseEntity<RegistroResponseDTO> registrarContribuyente(@RequestBody RegistroRequestDTO registroDTO) {

        // La validación de datos (ej. email único) debería hacerse aquí o en el servicio.
        if (registroDTO.getPassword() == null || registroDTO.getUsuario() == null) {
            return ResponseEntity.badRequest().body(new RegistroResponseDTO(null, "Faltan campos obligatorios (usuario, email, password).", 400));
        }

        RegistroResponseDTO response = keycloakUserService.registrarContribuyente(registroDTO);

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}