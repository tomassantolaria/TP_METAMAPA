package Repositorio;

import Modelos.Entidades.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Integer> {
    Ubicacion findByLatitudAndLongitud(double latitud, double longitud);
}
