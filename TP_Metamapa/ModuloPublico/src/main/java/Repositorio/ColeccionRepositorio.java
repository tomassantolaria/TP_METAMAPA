package Repositorio;

import Modelos.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColeccionRepositorio extends JpaRepository<Coleccion, Long> {

}
