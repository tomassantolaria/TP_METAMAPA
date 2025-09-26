package Repositorios;

import Modelos.Entidades.Localidad;
import Modelos.Entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepositorio extends JpaRepository<Localidad, Long> {
    Localidad findByLocalidadAndProvincia(String nombre_localidad, Provincia provincia);
}
