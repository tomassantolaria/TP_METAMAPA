package Repositorio;

import Modelos.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CriterioPertenenciaRepositorio extends JpaRepository<CriteriosDePertenencia, Long> {

}
