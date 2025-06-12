package Servicio;

import Modelos.DTOs.SolicitudDTO;
import Modelos.Entidades.Estado;
import Repositorio.SolicitudRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import Modelos.Entidades.Solicitud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class SolicitudService implements DetectorDeSpam{

    public void crearSolicitud(SolicitudDTO solicituddto){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaSolicitud =  LocalDate.parse(solicituddto.getFecha_creacion(), formato);
        String motivo = solicituddto.getMotivo();
        String idHecho = solicituddto.getIdHecho();
        if(motivo.length() < 500){
            Solicitud solicitud = new Solicitud(fechaSolicitud, motivo, idHecho, Estado.PENDIENTE);
            SolicitudRepository.guardarSolicitud(solicitud);
        }
        else throw new IllegalArgumentException("Solicitud rechazada por spam");
    }

    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }
}
