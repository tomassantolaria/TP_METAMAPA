package Repositorio;

import Modelos.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface CriterioPertenenciaRepositorio extends JpaRepository<CriteriosDePertenencia, Long> {
    CriteriosDePertenencia findByTituloAndMultimediaAndCategoriaAndFechaCargaDesdeAndFechaCargaHastaAndUbicacionAndFechaAcontecimientoDesdeAndFechaAcontecimientoHastaAndOrigen(String titulo, Boolean multimedia, Categoria categoria, LocalDateTime fechaCargaDesde, LocalDateTime fechaCargaHasta, Ubicacion ubicacion, LocalDateTime fechaAcontecimientoDesde, LocalDateTime fechaAcontecimientoHasta, OrigenCarga origen);
}
