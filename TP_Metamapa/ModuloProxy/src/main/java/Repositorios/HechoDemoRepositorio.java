package Repositorios;
import Modelos.Entidades.HechoDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HechoDemoRepositorio extends JpaRepository<HechoDemo, Long> {

    List<HechoDemo> findByPublicadoFalse();

}