package Modelos.Repositorios;
import Modelos.Entidades.Hecho;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuenteDemo_Hechos {

    private final List<Hecho> hechos = new ArrayList<>(); //me tira error si no inicializo

    public void guardarHechos(List<Hecho> nuevosHechos) {

        for (Hecho nuevo : nuevosHechos) {
            // por si ya hay uno con el mismo titulo entonces lo pisamos
            hechos.removeIf(h -> h.getTitulo().equalsIgnoreCase(nuevo.getTitulo()));
            hechos.add(nuevo);
        }
    }

    public List<Hecho> obtenerHechos(){
        return hechos;
    }

}
