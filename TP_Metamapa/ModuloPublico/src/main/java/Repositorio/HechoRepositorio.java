package Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;



@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {

}
