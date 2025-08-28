package Repositorio;

import Modelos.Entidades.Estado;
import Modelos.Entidades.Solicitud;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class SolicitudRepositorio {
    private final List<Solicitud> solicitudes;
    public SolicitudRepositorio(){
        this.solicitudes = new ArrayList<>();
    }

    public void guardarSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }

    public List<Solicitud> obtenerSolicitudesPendientes(){
        return solicitudes.stream()
                .filter(s->s.getEstado() == Estado.PENDIENTE)
                .toList();
    }
    public Solicitud buscarSolicitudPorId(String id){
        return this.solicitudes.stream()
                .filter(s->s.getIdSolcitud().equals(UUID.fromString(id))
                ).findFirst().orElseThrow(()-> new RuntimeException("No se encontro la solicitud"));
    }
}
