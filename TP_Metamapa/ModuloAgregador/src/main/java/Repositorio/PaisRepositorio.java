package Repositorio;

import Modelos.Entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, Long> {
    Pais findByPais(String nombre);
}
