package Repositorio;

import Modelos.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ColeccionRepositorio extends JpaRepository<Coleccion, Long> {

    @Query("SELECT h FROM Coleccion c JOIN c.hechos h " +
            "WHERE c.id = :coleccionId " +
            "AND (:categoria IS NULL OR h.categoria.nombre = :categoria) " +
            "AND (" +
            "    :contenidoMultimedia IS NULL" +
            "    OR (:contenidoMultimedia = TRUE AND h.contenido.contenido_multimedia IS NOT NULL AND h.contenido.contenido_multimedia <> '')" +
            "    OR (:contenidoMultimedia = FALSE AND (h.contenido.contenido_multimedia IS NULL OR h.contenido.contenido_multimedia = ''))" +
            ") " +
            "AND (:fechaCargaDesde IS NULL OR h.fecha_carga >= :fechaCargaDesde) " +
            "AND (:fechaCargaHasta IS NULL OR h.fecha_carga <= :fechaCargaHasta) " +
            "AND (:fechaHechoDesde IS NULL OR h.fecha >= :fechaHechoDesde) " +
            "AND (:fechaHechoHasta IS NULL OR h.fecha <= :fechaHechoHasta) " +
            "AND (:origenCarga IS NULL OR h.origen = :origenCarga) " +
            "AND (:titulo IS NULL OR h.titulo LIKE %:titulo%) " +
            "AND (:pais IS NULL OR h.ubicacion.pais.pais = :pais) " +
            "AND (:provincia IS NULL OR h.ubicacion.provincia.provincia = :provincia) " +
            "AND (:localidad IS NULL OR h.ubicacion.localidad.localidad = :localidad)")
    List<Hecho> filtrarHechosEnColeccion(
            @Param("coleccionId") Long coleccionId,
            @Param("categoria") String categoria,
            @Param("contenidoMultimedia") Boolean contenidoMultimedia,
            @Param("fechaCargaDesde") LocalDate fechaCargaDesde,
            @Param("fechaCargaHasta") LocalDate fechaCargaHasta,
            @Param("fechaHechoDesde") LocalDate fechaHechoDesde,
            @Param("fechaHechoHasta") LocalDate fechaHechoHasta,
            @Param("origenCarga") OrigenCarga origenCarga,
            @Param("titulo") String titulo,
            @Param("pais") String pais,
            @Param("provincia") String provincia,
            @Param("localidad") String localidad
    );

}