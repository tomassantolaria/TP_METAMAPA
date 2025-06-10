package Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Servicio.SolicitudService;
import Modelos.DTOs.SolicitudDTOInput;
import Modelos.DTOs.SolicitudDTOOutput;
import java.util.List;
import Modelos.DTOs.EstadoDTO;
import Servicio.SolicitudInvalidaException;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping()
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTOInput solicitud){
        try{
            solicitudService.crearSolicitud(solicitud);
            return ResponseEntity.ok("Se ha creado la solicitud.");
        }catch(SolicitudInvalidaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/estado?pendiente")
    public List<SolicitudDTOOutput> obtenerSolicitudesPendientes(){
        return solicitudService.solicitudesPendientes();
    }

    @PutMapping("/{id}")
    public void actualizarEstado(@PathVariable String idSolicitud, @RequestBody EstadoDTO estadoDTO){
        solicitudService.actualizarEstadoSolicitud(idSolicitud, estadoDTO.getEstado());
    }



}
