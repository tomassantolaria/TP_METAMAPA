package Modelos.Repositorios;

import java.util.ArrayList;
import java.util.List;
import Modelos.Entidades.Estado;

import org.springframework.stereotype.Repository;

import Modelos.Entidades.Solicitud;


@Repository
public class SolicitudRepositorio{
    private List<Solicitud> solicitudes;
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
                .filter(s->s.getIdSolcitud().equals(id))
                .findFirst()
                .orElse(null);
    }
}
