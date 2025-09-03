package Repositorio;
import Modelos.Entidades.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {

@Query("""
    SELECT COUNT(DISTINCT(CONCAT(h.idFuente, '-', h.origen_carga)))
    FROM Hecho h
    WHERE h.titulo = :titulo
      AND h.categoria = :categoria
      AND h.fecha = :fecha
      AND h.ubicacion = :ubicacion
      AND h.contribuyente = :contribuyente

""")
Long cantidadDeFuentesConHecho(
        @Param("titulo") String titulo,
       // @Param("descripcion") String descripcion,
        @Param("categoria") Categoria categoria,
        @Param("fecha") LocalDate fecha,
        @Param("ubicacion") Ubicacion ubicacion,
        @Param("contribuyente") Contribuyente contribuyente
     //   @Param("contenido") Contenido contenido
);

    @Query(value = """
    SELECT COUNT(DISTINCT CONCAT(h.id_fuente, '-', h.origen_carga))
    FROM Hechos h
""", nativeQuery = true) //
    Long cantidadFuentes();

    @Query("""
    SELECT COUNT(DISTINCT(CONCAT(h.idFuente, '-', h.origen_carga)))
    FROM Hecho h
    WHERE h.titulo = :titulo
    AND (
         h.descripcion != :descripcion
      OR h.categoria != :categoria
      OR h.fecha != :fecha
      OR h.ubicacion != :ubicacion
      OR h.contribuyente != :contribuyente
      OR h.contenido != :contenido)
""")
    Long cantidadDeFuentesConMismoTituloDiferentesAtributos(
            @Param("titulo") String titulo,
            @Param("descripcion") String descripcion,
            @Param("categoria") Categoria categoria,
            @Param("fecha") LocalDate fecha,
            @Param("ubicacion") Ubicacion ubicacion,
            @Param("contribuyente") Contribuyente contribuyente,
            @Param("contenido") Contenido contenido
    );

    //si al menos dos fuentes contienen un mismo hecho y ninguna otra fuente
    //contiene otro de igual título pero diferentes atributos, se lo considera consensuado;



}
