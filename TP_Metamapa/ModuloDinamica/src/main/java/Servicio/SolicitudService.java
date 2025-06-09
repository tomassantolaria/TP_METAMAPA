package Servicio;

import Modelos.DTOs.SolicitudDTO;
import Modelos.Entidades.Estado;
import Repositorio.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelos.Entidades.Solicitud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class SolicitudService implements DetectorDeSpam{

    @Autowired
    SolicitudRepository solicitudRepository;

    public void crearSolicitud(SolicitudDTO solicituddto){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaSolicitud =  LocalDate.parse(solicituddto.getFecha_creacion(), formato);
        String motivo = solicituddto.getMotivo();
        String idHecho = solicituddto.getIdHecho();
        if(motivo.length() < 500){
            Solicitud solicitud = new Solicitud(fechaSolicitud, motivo, idHecho, Estado.PENDIENTE);
            solicitudRepository.guardarSolicitud(solicitud);
        }
        else throw new SolicitudInvalidaException("Solicitud rechazada por spam");
    }

    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }

    public List<SolicitudDTO> solicitudesPendientes(){
        List<Solicitud> solicitudes = solicitudRepository.getSolicitudesPendientes();
        return solicitudes.stream().map(this::pasarADTO).toList();
    }
    private SolicitudDTO pasarADTO(Solicitud solicitud){
        return new SolicitudDTO(solicitud.getIdHecho(), solicitud.getMotivo(), solicitud.getFecha_creacion().toString());
    }
}
