package Repositorio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import Modelos.Entidades.Solicitud;


@Repository
public class SolicitudRepository {
    private static final List<Solicitud> solicitudes = new ArrayList<>();
    public static void guardarSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }
}
