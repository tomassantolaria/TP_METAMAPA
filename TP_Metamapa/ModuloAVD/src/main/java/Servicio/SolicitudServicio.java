package Servicio;

import Modelos.DTOs.SolicitudDTOOutput;
import Modelos.Entidades.Estado;
import Modelos.Entidades.Hecho;
import Modelos.Entidades.Solicitud;
import Repositorio.HechoRepositorio;
import Repositorio.SolicitudRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class SolicitudServicio {

    final
    SolicitudRepositorio solicitudRepositorio;
    HechoRepositorio hechoRepositorio;

    public SolicitudServicio(SolicitudRepositorio solicitudRepositorio) {
        this.solicitudRepositorio = solicitudRepositorio;
    }


    public List<SolicitudDTOOutput> solicitudesPendientes(){
        List<Solicitud> solicitudes = solicitudRepositorio.findByEstado(Estado.PENDIENTE);
        return solicitudes.stream().map(this::pasarADTO).toList();
    }
    private SolicitudDTOOutput pasarADTO(Solicitud solicitud){
        return new SolicitudDTOOutput(solicitud.getIdSolcitud().toString(), solicitud.getMotivo(), solicitud.getHecho().getId(), solicitud.getFecha_creacion());
    }


    public void actualizarEstadoSolicitud(Long idSolicitud, Estado nuevoEstado){
        Solicitud solicitud = solicitudRepositorio.findById(idSolicitud).orElseThrow(()-> new RuntimeException("No se encontro la solicitud"));
        solicitud.setEstado(nuevoEstado);
        if(nuevoEstado == Estado.ACEPTADA){
            Hecho hecho = hechoRepositorio.findById(solicitud.getHecho().getId()).orElseThrow(()-> new RuntimeException("No se encontro el hecho"));
            hecho.eliminarse();
            hechoRepositorio.save(hecho);
        }
        solicitudRepositorio.save(solicitud);
    }


}
