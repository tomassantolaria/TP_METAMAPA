package Repositorio;

import Modelos.Entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
    Categoria findByNombre(String nombre);

}