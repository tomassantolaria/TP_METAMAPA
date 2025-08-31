package Repositorio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {

    //IMPLEMENTAR FUNCION EN SQL
    long countByTituloAndDescripcionAndCategoriaAndContenidoAndFechaAndUbicacion();
    @Query("select")

    //si al menos dos fuentes contienen un mismo hecho y ninguna otra fuente
    //contiene otro de igual título pero diferentes atributos, se lo considera consensuado;

/*    public Boolean cantidadFuentesConTitulo (String titulo, Set <Long> fuentes) {
        for (Hecho hecho : hechos.values()) {
            if(hecho.getTitulo().equals(titulo) && !fuentes.contains(hecho.getIdFuente())) {
               return false;
            }
        }
        return true;
    }

    public  Set <Long> cantidadFuentesConHecho (Hecho hecho) {
        Set <Long> fuentes = new HashSet<>();
        for (Hecho hecho1 : hechos.values()) {
            if(hecho1.esIgualA(hecho)) {
                fuentes.add(hecho.getIdFuente());
            }
        }
        return fuentes;
    }
*/


}
