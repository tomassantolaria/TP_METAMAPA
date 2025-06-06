package Servicio;

import Modelos.DTOs.SolicitudDTO;
import Modelos.Entidades.Estado;
import Repositorio.SolicitudRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import Modelos.Entidades.Solicitud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Modelé acá lo de ver si es spam o no, asi ya le cambio el estado
@Service
public class SolicitudService implements DetectorDeSpam{

    public void crearSolicitud(SolicitudDTO solicituddto){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate fechaSolicitud =  LocalDate.parse(solicituddto.getFecha_creacion(), formato);
        String motivo = solicituddto.getMotivo();
        Integer idHecho = Integer.parseInt(solicituddto.getIdHecho());
        Solicitud solicitud = new Solicitud(fechaSolicitud, motivo, idHecho, Estado.PENDIENTE);

        if(esSpam(solicitud.getMotivo())){
            solicitud.setEstado(Estado.RECHAZADA);
        }
        SolicitudRepository.guardarSolicitud(solicitud);

    }

    @Override
    public boolean esSpam(String texto) {
        return texto.length()>500;
    }
}
