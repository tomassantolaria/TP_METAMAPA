package Servicio;

import Modelos.SolicitudDTOInput;
import Modelos.Entidades.Estado;
import Modelos.Entidades.Solicitud;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import Repositorio.SolicitudRepositorio;
import Servicio.Solicitudes.DetectorDeSpam;
import org.springframework.stereotype.Service;
import java.time.LocalDate;



@Service
public class SolicitudServicio implements DetectorDeSpam {

    SolicitudRepositorio solicitudRepositorio;
    HechoRepositorio  hechoRepositorio;


    public void crearSolicitud(SolicitudDTOInput solicituddto){
        LocalDate fechaSolicitud =  LocalDate.now();
        String motivo = solicituddto.getMotivo();
        Hecho hecho = hechoRepositorio.findById(solicituddto.getIdHecho()).orElseThrow(() -> new RuntimeException("Hecho no encontrado"));
        if(!esSpam(motivo)){
            Solicitud solicitud = new Solicitud(null, fechaSolicitud, motivo, hecho, Estado.PENDIENTE);
            solicitudRepositorio.save(solicitud);
        }
        else{
            Solicitud solicitud = new Solicitud(null, fechaSolicitud, motivo, hecho, Estado.SPAM);
            solicitudRepositorio.save(solicitud);
        }

    }


    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }
}
