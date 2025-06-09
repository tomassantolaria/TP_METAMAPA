package Controlador;

import Servicio.SolicitudInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Servicio.SolicitudService;
import Modelos.DTOs.SolicitudDTO;

@RestController
public class SolicitudController {

    @Autowired
    SolicitudService solicitudService;

    @PostMapping("/solicitud")
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTO solicitud){
        try{
            solicitudService.crearSolicitud(solicitud);
            return ResponseEntity.ok("Se ha creado la solicitud.");
        }catch(SolicitudInvalidaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
