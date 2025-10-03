package Repositorio;

import Modelos.Hecho;
import Modelos.Provincia;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {

    @Query("""
        SELECT FUNCTION('HOUR', h.fecha)
        FROM Hecho H
        left join Categoria C
        WHERE C.nombre = :categoria
        GROUP BY FUNCTION('HOUR', h.fecha)
        ORDER BY COUNT(H.id) DESC
    """)
    List<Integer> getHoraConMasHechos(String categoria, Pageable pageable);

    @Query("""
        select c.id
        from Coleccion c
    """)
    List<Long> getColeccionId();

}