package Repositorio;

import Modelos.Entidades.Contribuyente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContribuyenteRepositorio extends JpaRepository<Contribuyente, String>{
    Contribuyente findByUsuario(String nombre);
}
