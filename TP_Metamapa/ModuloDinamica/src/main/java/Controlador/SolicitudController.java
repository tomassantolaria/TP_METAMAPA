package Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Servicio.SolicitudService;
import Modelos.DTOs.SolicitudDTO;

@RestController
@RequestMapping("/api")
public class SolicitudController {

    private final SolicitudService solicitudService;
    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/solicitud")
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTO solicitud){
        try{
            solicitudService.crearSolicitud(solicitud);
            return ResponseEntity.ok("Se ha creado la solicitud.");
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Solicitud rechazada");
        }
    }

}
