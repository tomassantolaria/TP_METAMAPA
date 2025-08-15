package Controlador.ApisAdministrativas;

import Modelos.DTOs.EstadoDTO;
import Modelos.DTOs.SolicitudDTOInput;
import Modelos.DTOs.SolicitudDTOOutput;
import Servicio.SolicitudServicio;
import Servicio.Solicitudes.SolicitudInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public void actualizarEstado(@PathVariable String idSolicitud, @RequestBody EstadoDTO estadoDTO){
        solicitudServicio.actualizarEstadoSolicitud(idSolicitud, estadoDTO.getEstado());
    }



}