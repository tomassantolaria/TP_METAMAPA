package Servicio;

import Modelos.DTOs.SolicitudDTOOutput;
import Modelos.Entidades.Estado;
import Modelos.Entidades.Hecho;
import Modelos.Entidades.Solicitud;
import Repositorio.HechoRepositorio;
import Repositorio.SolicitudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class SolicitudServicio {

    @Autowired
    SolicitudRepositorio solicitudRepositorio;
    HechoRepositorio hechoRepositorio;


    public List<SolicitudDTOOutput> solicitudesPendientes(){
        List<Solicitud> solicitudes = solicitudRepositorio.obtenerSolicitudesPendientes();
        return solicitudes.stream().map(this::pasarADTO).toList();
    }
    private SolicitudDTOOutput pasarADTO(Solicitud solicitud){
        return new SolicitudDTOOutput(solicitud.getIdSolcitud().toString(), solicitud.getMotivo(), solicitud.getIdHecho(), solicitud.getFecha_creacion());
    }


    public void actualizarEstadoSolicitud(String idSolicitud, Estado nuevoEstado){
        Solicitud solicitud = solicitudRepositorio.buscarSolicitudPorId(idSolicitud);
        solicitud.setEstado(nuevoEstado);
        if(nuevoEstado == Estado.ACEPTADA){
            Hecho hecho = hechoRepositorio.buscarHechoPorId(solicitud.getIdHecho());
            hecho.eliminarse();
        }
        solicitudRepositorio.guardarSolicitud(solicitud);
    }


}
