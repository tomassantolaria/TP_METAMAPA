package Controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Servicio.SolicitudService;
import Modelos.DTOs.SolicitudDTO;

@RestController
public class SolicitudController {

    @PostMapping("/solicitud")
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTO solicitud){
        SolicitudService.crearSolicitud(solicitud);
        return ResponseEntity.ok("Se ha creado la solicitud.");
    }

}
