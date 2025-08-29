package Repositorio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Getter @Setter
public class HechoRepositorio {
    private final Map<Long, Hecho> hechos = new HashMap<>();

    public void agregarHecho(Hecho hecho) {
        if (hecho != null && !hechos.containsKey(hecho.getId())) {
            hechos.put(hecho.getId(), hecho);
            hecho.setVisible(true);
        }
    }
    public List<Hecho> allHechos(){
        return new ArrayList<>(hechos.values());
    }

    public Boolean cantidadFuentesConTitulo (String titulo, Set <Long> fuentes) {

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



}
