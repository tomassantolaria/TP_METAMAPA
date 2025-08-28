package Repositorio;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.*;

@Repository
public class HechoRepositorio {
    private final Map<UUID, Hecho> hechos = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Hecho> getHechos() {
        return new ArrayList<>(hechos.values());
    }


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

}
