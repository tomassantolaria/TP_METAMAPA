package Repositorio;

import Modelos.Entidades.Pais;
import Modelos.Entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long>{
    Provincia findByProvinciaAndPais(String nombre, Pais pais);
}