package Repositorio;

import Modelos.Entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepositorio extends JpaRepository<Pais, Long> {
}
