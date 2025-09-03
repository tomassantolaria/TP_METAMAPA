package Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.List;


@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {


     List<Hecho> findByFuente(Long fuente);

}
