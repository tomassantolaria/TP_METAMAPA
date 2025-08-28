package Servicio;

import Modelos.DTOs.SolicitudDTOInput;
import Modelos.Entidades.Estado;
import Modelos.Entidades.Solicitud;
import Repositorio.SolicitudRepositorio;
import Servicio.Solicitudes.DetectorDeSpam;
import Servicio.Solicitudes.SolicitudInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;


@Service
public class SolicitudServicio implements DetectorDeSpam {

    @Autowired
    SolicitudRepositorio solicitudRepositorio;


    public void crearSolicitud(SolicitudDTOInput solicituddto){
        UUID idSolicitud = UUID.randomUUID();
        LocalDate fechaSolicitud =  LocalDate.now();
        String motivo = solicituddto.getMotivo();
        UUID idHecho = solicituddto.getIdHecho();
        if(!esSpam(motivo)){
            Solicitud solicitud = new Solicitud(idSolicitud, fechaSolicitud, motivo, idHecho, Estado.PENDIENTE);
            solicitudRepositorio.guardarSolicitud(solicitud);
        }
        else throw new SolicitudInvalidaException("Solicitud rechazada por spam");
    }


    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }
}
