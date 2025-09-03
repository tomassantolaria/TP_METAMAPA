package Repositorio;

import Modelos.Entidades.Estado;
import Modelos.Entidades.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public interface SolicitudRepositorio extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findByEstado(Estado estado);
}
