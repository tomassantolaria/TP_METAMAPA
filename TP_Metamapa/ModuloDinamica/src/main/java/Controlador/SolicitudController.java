package Controlador;

import Servicio.SolicitudInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Servicio.SolicitudService;
import Modelos.DTOs.SolicitudDTO;

import java.util.List;

@RestController
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("/solicitudes")
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTO solicitud){
        try{
            solicitudService.crearSolicitud(solicitud);
            return ResponseEntity.ok("Se ha creado la solicitud.");
        }catch(SolicitudInvalidaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/solicitudes/estado?pendiente")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPendientes(){
        List<SolicitudDTO> solicitudes = solicitudService.solicitudesPendientes();
        return ResponseEntity.ok(solicitudes);
    }

}
