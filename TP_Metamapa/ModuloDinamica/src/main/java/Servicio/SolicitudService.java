package Servicio;

import Modelos.DTOs.SolicitudDTOInput;
import Modelos.DTOs.SolicitudDTOOutput;
import Modelos.Entidades.Estado;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepository;
import Repositorio.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelos.Entidades.Solicitud;

import java.time.LocalDate;
import java.util.List;


@Service
public class SolicitudService implements DetectorDeSpam{

    @Autowired
    SolicitudRepository solicitudRepository;
    HechoRepository hechoRepository;

    public void crearSolicitud(SolicitudDTOInput solicituddto){
        String idSolicitud = java.util.UUID.randomUUID().toString();
        LocalDate fechaSolicitud =  LocalDate.now();
        String motivo = solicituddto.getMotivo();
        String idHecho = solicituddto.getIdHecho();
        if(!esSpam(motivo)){
            Solicitud solicitud = new Solicitud(idSolicitud, fechaSolicitud, motivo, idHecho, Estado.PENDIENTE);
            solicitudRepository.guardarSolicitud(solicitud);
        }
        else throw new SolicitudInvalidaException("Solicitud rechazada por spam");
    }

    public List<SolicitudDTOOutput> solicitudesPendientes(){
        List<Solicitud> solicitudes = solicitudRepository.obtenerSolicitudesPendientes();
        return solicitudes.stream().map(this::pasarADTO).toList();
    }
    private SolicitudDTOOutput pasarADTO(Solicitud solicitud){
        return new SolicitudDTOOutput(solicitud.getIdSolcitud(), solicitud.getMotivo(), solicitud.getIdHecho(), solicitud.getFecha_creacion().toString());
    }


    public void actualizarEstadoSolicitud(String idSolicitud, Estado nuevoEstado){
        Solicitud solicitud = solicitudRepository.buscarSolicitudPorId(idSolicitud);
        solicitud.setEstado(nuevoEstado);
        if(nuevoEstado == Estado.ACEPTADA){
            Hecho hecho = hechoRepository.buscarHechoPorId(solicitud.getIdHecho());
            hecho.modificarVisibilidad();
            hechoRepository.guardarHecho(hecho);
            //Acá le debería avisar al agregador
        }
        solicitudRepository.guardarSolicitud(solicitud);
    }


    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }
}
