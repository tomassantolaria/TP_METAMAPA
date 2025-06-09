package Controlador;

import Modelos.DTOs.SolicitudDTO;
import Servicio.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AdminSolicitudControlador {

    @Autowired
    SolicitudService solicitudService;

    @GetMapping("/admin/solicitudes/estado?pendiente")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPendientes(){
        List<SolicitudDTO> solicitudes = solicitudService.solicitudesPendientes();
        return ResponseEntity.ok(solicitudes);
    }
}
