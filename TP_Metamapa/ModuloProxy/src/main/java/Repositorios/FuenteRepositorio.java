package Repositorios;

import Modelos.Entidades.Fuente;
import Modelos.Entidades.TipoFuente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface FuenteRepositorio extends JpaRepository<Fuente, Long> {
    List<Fuente> findByTipoFuente(TipoFuente tipoFuente);
}
