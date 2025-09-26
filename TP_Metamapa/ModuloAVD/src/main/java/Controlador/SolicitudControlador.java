package Controlador;

import Modelos.DTOs.EstadoDTO;
import Modelos.DTOs.SolicitudDTOOutput;
import Servicio.SolicitudServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudControlador {

    @Autowired
    private SolicitudServicio solicitudServicio;


    @GetMapping("/estado?pendiente")
    public ResponseEntity<List<SolicitudDTOOutput>> obtenerSolicitudesPendientes(){
        try {
            return ResponseEntity.status(200).body(solicitudServicio.solicitudesPendientes());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(solicitudServicio.solicitudesPendientes());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO){
        try {
            solicitudServicio.actualizarEstadoSolicitud(id, estadoDTO.getEstado());
            return ResponseEntity.status(200).body("Estado actualizado correctamente.");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al actualizar el estado.");
        }
    }



}