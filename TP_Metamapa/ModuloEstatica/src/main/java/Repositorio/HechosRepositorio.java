package Repositorio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import Modelos.Entidades.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HechosRepositorio extends JpaRepository<Hecho, Long> {
    // addHecho = save(Hecho) --> te devuelvele el hecho creado con el ID
    // addAllHecho = saveAll(List<Hecho>)
    // allHecho = findAll()

    List<Hecho> findAllByProcesadoFalse();

    @Query("""
    SELECT COUNT(*)
    FROM Hecho h
    WHERE h.archivo.id = :id
      AND h.titulo = :titulo
      AND h.descripcion = :descripcion
      AND h.categoria =:categoria
      AND h.fechaAcontecimiento = :fechaAcontecimiento
    """)

    Integer noExisteHecho(
            @Param("id") Long id,
            @Param("titulo") String titulo,
            @Param("descripcion") String descripcion,
            @Param("categoria") String categoria,
            //@Param("latitud") String latitud,
           // @Param("longitud") String longitud
           @Param("fechaAcontecimiento") LocalDate fechaAcontecimiento
    );

//    @Query("""
//    SELECT h.archivo
//    FROM Hecho h
//    WHERE h.archivo.path = :path
//  """)
//
//    Archivo existePath(
//            @Param("path") String path
//    );

}

/*
package Repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
      AND h.titulo = :titulo

@Repository
public interface HechosRepositorio JpaRepository<Hecho, Long>{{
// addHecho = save(Hecho) --> te devuelvele el hecho creado con el ID
// addAllHecho = saveAll(List<Hecho>)
// allHecho = findAll()




@Transactional
public List<Hecho> allHechosNoEnviados() {
    // 1. Traigo los no procesados
    List<Hecho> hechosNoEnviados = entityManager
        .createQuery("SELECT h FROM Hecho h WHERE h.procesado = false", Hecho.class)
        .getResultList();

    // 2. Los marco como procesados
    for (Hecho h : hechosNoEnviados) {
        h.setProcesado(true);
    }

    // 3. Como están en el contexto de persistencia, al commit se hace UPDATE en DB
    return hechosNoEnviados; // devuelven con los mismos id que en la base
}

}@Query("SELECT h FROM Hecho h " +
            "WHERE (:categoria IS NULL OR h.categoria = :categoria) " +
            "AND (:contenidoMultimedia IS NULL OR h.contenido.contenido_multimedia = :contenidoMultimedia) " +
            "AND (:fechaCargaDesde IS NULL OR h.fecha_carga >= :fechaCargaDesde) " +
            "AND (:fechaCargaHasta IS NULL OR h.fecha_carga<= :fechaCargaHasta) " +
            "AND (:fechaHechoDesde IS NULL OR h.fecha>= :fechaHechoDesde) " +
            "AND (:fechaHechoHasta IS NULL OR h.fecha <= :fechaHechoHasta) " +
            "AND (:origenCarga IS NULL OR h.origen_carga = :origenCarga) " +
            "AND (:titulo IS NULL OR h.titulo LIKE %:titulo%) " +
            "AND (:pais IS NULL OR h.ubicacion.pais.nombre_pais = :pais) " +
            "AND (:provincia IS NULL OR h.ubicacion.provincia.nombre_provincia = :provincia) " +
            "AND (:localidad IS NULL OR h.ubicacion.localidad.nombre_localidad = :localidad)")
    List<Hecho> filtrarHechos(
            @Param("categoria") String categoria,
            @Param("contenidoMultimedia") Boolean contenidoMultimedia,
            @Param("fechaCargaDesde") LocalDate fechaCargaDesde,
            @Param("fechaCargaHasta") LocalDate fechaCargaHasta,
            @Param("fechaHechoDesde") LocalDate fechaHechoDesde,
            @Param("fechaHechoHasta") LocalDate fechaHechoHasta,
            @Param("origenCarga") String origenCarga,
            @Param("titulo") String titulo,
            @Param("pais") String pais,
            @Param("provincia") String provincia,
            @Param("localidad") String localidad
    );
 */


