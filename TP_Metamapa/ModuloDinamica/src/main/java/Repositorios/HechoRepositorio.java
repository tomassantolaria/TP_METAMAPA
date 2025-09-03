package Repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import Modelos.Entidades.*;


@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long>{
}