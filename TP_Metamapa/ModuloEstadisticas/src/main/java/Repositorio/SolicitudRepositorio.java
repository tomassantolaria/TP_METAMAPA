package Repositorio;

import Modelos.Estado;
import Modelos.Provincia;
import Modelos.Solicitud;
import org.hibernate.mapping.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SolicitudRepositorio extends JpaRepository<Solicitud, Long> {

    Long countSolicitudByEstado(Estado estado);

}