package Repositorio;

import Modelos.Entidades.Consenso.Consenso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsensoRepositorio extends JpaRepository<Consenso, Long> {

    Consenso findByName(String nombre);
}
