package Repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EstadisticasRepositorio extends JpaRepository<Estadisticas, Long> {

    @Query("""
        SELECT h.ubicacion.provincia.provincia
        FROM Coleccion c
        JOIN c.hechos h
        WHERE c.id = :idColeccion
        GROUP BY h.ubicacion.provincia.provincia
        ORDER BY COUNT(h.id_hecho) DESC
    """)
    List <String> getProvinciaConMasHechos(@Param("idColeccion") Long idColeccion, Pageable pageable);


    @Query("""
        select H.categoria.nombre
        from Hecho H
        group by H.categoria.nombre
        order by count(H.id_hecho) desc
    """)
    List<String> getCategoriaConMasHechos(Pageable pageable);


    @Query("""
        SELECT h.ubicacion.provincia.provincia
        FROM Hecho h
        WHERE h.categoria.nombre = :categoria
        GROUP BY h.ubicacion.provincia.provincia
        ORDER BY COUNT(h.id_hecho) DESC
    """)    
    List<String> getProvinciaConMasHechosDeCategoria(String categoria, Pageable pageable);

    
    @Query("""
        SELECT FUNCTION('HOUR', h.fecha)
        FROM Hecho H
        WHERE H.categoria.nombre = :categoria
        GROUP BY FUNCTION('HOUR', h.fecha)
        ORDER BY COUNT(H.id_hecho) DESC
    """)
    List<Integer> getHoraConMasHechos(String categoria, Pageable pageable);


    @Query("""
        select count(*) 
        from Solicitud S
        where S.estado = "SPAM"
    """)
    Long getCantidadSolicitudesSpam(Pageable pageable);

}