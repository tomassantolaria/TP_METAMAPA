package Repositorio;


import Modelos.Entidades.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SolicitudRepositorio extends JpaRepository<Solicitud, Long> {

}
