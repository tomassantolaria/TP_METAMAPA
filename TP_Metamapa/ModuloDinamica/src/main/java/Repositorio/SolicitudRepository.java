package Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Modelos.Entidades.Estado;

import org.springframework.stereotype.Repository;

import Modelos.Entidades.Solicitud;


@Repository
public class SolicitudRepository {
    public List<Solicitud> solicitudes = new ArrayList<>();
    public void guardarSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }
    public List<Solicitud> getSolicitudesPendientes(){
        return solicitudes.stream().filter(s->s.getEstado() == Estado.PENDIENTE).toList();

    }
}
