package Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Servicios.SolicitudServicio;
import Modelos.DTOs.SolicitudDTOInput;
import Modelos.DTOs.SolicitudDTOOutput;
import java.util.List;
import Modelos.DTOs.EstadoDTO;
import Servicios.SolicitudInvalidaException;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudControlador {

    @Autowired
    private SolicitudServicio solicitudServicio;

    @PostMapping()
    public ResponseEntity<String> crearSolicitud(@RequestBody SolicitudDTOInput solicitud){
        try{
            solicitudServicio.crearSolicitud(solicitud);
            return ResponseEntity.ok("Se ha creado la solicitud.");
        }catch(SolicitudInvalidaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/estado?pendiente")
    public List<SolicitudDTOOutput> obtenerSolicitudesPendientes(){
        return solicitudServicio.solicitudesPendientes();
    }

    @PutMapping("/{id}")
    public void actualizarEstado(@PathVariable String idSolicitud, @RequestBody EstadoDTO estadoDTO){
        solicitudServicio.actualizarEstadoSolicitud(idSolicitud, estadoDTO.getEstado());
    }



}