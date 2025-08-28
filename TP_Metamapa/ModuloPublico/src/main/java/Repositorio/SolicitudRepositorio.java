package Repositorio;


import Modelos.Entidades.Solicitud;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class SolicitudRepositorio {
    private final List<Solicitud> solicitudes;
    public SolicitudRepositorio(){
        this.solicitudes = new ArrayList<>();
    }

    public void guardarSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }

}
