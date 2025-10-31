package Repositorio;

import Modelos.Entidades.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
    Optional<Archivo> findByPath(String path);
}
