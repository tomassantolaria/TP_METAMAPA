package Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.*;

@Repository
public interface HechoRepositorio extends JpaRepository<Hecho, Long> {

    /*
    public Boolean cantidadFuentesConTitulo (String titulo, Set <UUID> fuentes) {

        for (Hecho hecho : hechos.values()) {
            if(hecho.titulo.equals(titulo) && !fuentes.contains(hecho.idFuente)) {
               return false;
            }
        }
        return true;
    }

    public  Set <UUID> cantidadFuentesConHecho (Hecho hecho) {

        Set <UUID> fuentes = new HashSet<>();
        for (Hecho hecho1 : hechos.values()) {
            if(hecho1.esIgualA(hecho)) {
                fuentes.add(hecho.getIdFuente());
            }
        }
        return fuentes;
    }

    public List<Hecho> hechosConFuente (UUID fuente) {
        return hechos.values().stream().filter(hecho -> hecho.getIdFuente().equals(fuente) && hecho.isVisible()).toList();
    }
    */
     */
}
