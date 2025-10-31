package Repositorio;

import Modelos.Entidades.Localidad;
import Modelos.Entidades.Pais;
import Modelos.Entidades.Provincia;
import Modelos.Entidades.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Long> {
    Ubicacion findByLocalidadAndProvinciaAndPais(Localidad localidad, Provincia provincia, Pais pais);
}