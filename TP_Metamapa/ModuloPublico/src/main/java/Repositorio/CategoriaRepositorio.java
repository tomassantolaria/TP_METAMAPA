package Repositorio;

import Modelos.Entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository <Categoria, Long> {

}
