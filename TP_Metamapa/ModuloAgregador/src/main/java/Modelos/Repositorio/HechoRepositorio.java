package Modelos.Repositorio;
import org.springframework.stereotype.Repository;
import Modelos.Entidades.Hecho;

import java.util.*;

@Repository
public class HechoRepositorio {
    private final Map<String, Hecho> hechos = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Hecho> getHechos() {
        return new ArrayList<>(hechos.values());
    }

    public void eliminarHecho(String id) { this.hechos.remove(id); }

}
