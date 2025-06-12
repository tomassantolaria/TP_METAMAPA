package Servicios;

import Modelos.DTOs.SolicitudDTOInput;
import Modelos.DTOs.SolicitudDTOOutput;
import Modelos.Entidades.Estado;
import Modelos.Entidades.Hecho;
import Modelos.Repositorios.HechoRepositorio;
import Modelos.Repositorios.SolicitudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelos.Entidades.Solicitud;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class SolicitudServicio implements DetectorDeSpam {

    @Autowired
    SolicitudRepositorio solicitudRepositorio;
    HechoRepositorio hechoRepositorio;

    public void crearSolicitud(SolicitudDTOInput solicituddto){
        String idSolicitud = UUID.randomUUID().toString();
        LocalDate fechaSolicitud =  LocalDate.now();
        String motivo = solicituddto.getMotivo();
        String idHecho = solicituddto.getIdHecho();
        if(!esSpam(motivo)){
            Solicitud solicitud = new Solicitud(idSolicitud, fechaSolicitud, motivo, idHecho, Estado.PENDIENTE);
            solicitudRepositorio.guardarSolicitud(solicitud);
        }
        else throw new SolicitudInvalidaException("Solicitud rechazada por spam");
    }

    public List<SolicitudDTOOutput> solicitudesPendientes(){
        List<Solicitud> solicitudes = solicitudRepositorio.obtenerSolicitudesPendientes();
        return solicitudes.stream().map(this::pasarADTO).toList();
    }
    private SolicitudDTOOutput pasarADTO(Solicitud solicitud){
        return new SolicitudDTOOutput(solicitud.getIdSolcitud(), solicitud.getMotivo(), solicitud.getIdHecho(), solicitud.getFecha_creacion().toString());
    }


    public void actualizarEstadoSolicitud(String idSolicitud, Estado nuevoEstado){
        Solicitud solicitud = solicitudRepositorio.buscarSolicitudPorId(idSolicitud);
        solicitud.setEstado(nuevoEstado);
        if(nuevoEstado == Estado.ACEPTADA){
            Hecho hecho = hechoRepositorio.buscarHechoPorId(solicitud.getIdHecho());
            hecho.modificarVisibilidad();
            hechoRepositorio.guardarHecho(hecho);
            //Acá le debería avisar al agregador
        }
        solicitudRepositorio.guardarSolicitud(solicitud);
    }


    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }
}
