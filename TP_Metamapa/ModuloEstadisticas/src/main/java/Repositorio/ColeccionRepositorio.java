package Repositorio;

import Modelos.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColeccionRepositorio extends JpaRepository<Coleccion,Long> {
}
