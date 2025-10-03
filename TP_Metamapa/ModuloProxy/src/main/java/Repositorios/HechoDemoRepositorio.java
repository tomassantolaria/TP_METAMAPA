package Repositorios;
import Modelos.DTOs.HechoDTO;
import Modelos.Entidades.HechoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HechoDemoRepositorio extends JpaRepository<HechoEntity, Long> {
    List<HechoEntity> findByUrlFuente(String urlFuente);
}