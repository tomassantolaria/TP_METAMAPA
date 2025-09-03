package Controlador;

import Modelos.DTOs.EstadoDTO;
import Modelos.DTOs.SolicitudDTOOutput;
import Servicio.SolicitudServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudControlador {

    @Autowired
    private SolicitudServicio solicitudServicio;


    @GetMapping("/estado?pendiente")
    public List<SolicitudDTOOutput> obtenerSolicitudesPendientes(){
        return solicitudServicio.solicitudesPendientes();
    }

    @PutMapping("/{id}")
    public void actualizarEstado(@PathVariable Long id, @RequestBody EstadoDTO estadoDTO){
        solicitudServicio.actualizarEstadoSolicitud(id, estadoDTO.getEstado());
    }



}