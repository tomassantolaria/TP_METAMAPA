package Repositorio;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import Modelos.*;


@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {

    @Query("""
        SELECT p.provincia
        FROM Coleccion c
        left JOIN Hecho h
        left join Ubicacion u
        left join Provincia p
        WHERE c.id = :idColeccion
        GROUP BY p.provincia
        ORDER BY COUNT(h.id) DESC
    """)
    List <String> getProvinciaConMasHechos(@Param("idColeccion") Long idColeccion, Pageable pageable);


    @Query("""
        select c.nombre
        from Hecho H
        left join Categoria c
        group by c.nombre
        order by count(H.id) desc
    """)
    List<String> getCategoriaConMasHechos(Pageable pageable);


    @Query("""
        SELECT p.provincia
        FROM Hecho h
        left join Categoria c
        left join Ubicacion u
        left join Provincia p
        WHERE c.nombre = :categoria
        GROUP BY p.provincia
        ORDER BY COUNT(h.id) DESC
    """)    
    List<String> getProvinciaConMasHechosDeCategoria(String categoria, Pageable pageable);


}