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
        cross JOIN Hecho h
        join Ubicacion u ON h.ubicacion.idUbicacion = u.idUbicacion
        join Provincia p ON u.provincia.idProvincia = p.idProvincia
        WHERE c.id = :idColeccion
        GROUP BY p.provincia
        ORDER BY COUNT(h.id) DESC
    """)
    List <String> getProvinciaConMasHechos(@Param("idColeccion") Long idColeccion, Pageable pageable);


    @Query("""
        SELECT p.provincia
        FROM Hecho h
        left join Categoria c ON h.categoria.id = c.id
        left join Ubicacion u ON h.ubicacion.idUbicacion = u.idUbicacion
        left join Provincia p ON u.provincia.idProvincia = p.idProvincia
        WHERE c.nombre = :categoria
        GROUP BY p.provincia
        ORDER BY COUNT(h.id) DESC
    """)    
    List<String> getProvinciaConMasHechosDeCategoria(@Param("categoria") String categoria, Pageable pageable);


}