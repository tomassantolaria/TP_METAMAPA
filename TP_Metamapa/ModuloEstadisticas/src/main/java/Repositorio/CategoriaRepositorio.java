package Repositorio;

import Modelos.Categoria;
import Modelos.Provincia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    @Query("""
        select C.nombre
        from Hecho H
        left join Categoria C
        group by C.nombre
        order by count(H.id) desc
    """)
    List<String> getCategoriaConMasHechos(Pageable pageable);

    @Query("""
           select distinct c.nombre
           from Hecho h
           left join Categoria c
           """)
    List<String> getCategorias();

}