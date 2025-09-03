package Repositorios;

import Modelos.Entidades.Contribuyente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContribuyenteRepositorio extends JpaRepository<Contribuyente, Long>{

}
