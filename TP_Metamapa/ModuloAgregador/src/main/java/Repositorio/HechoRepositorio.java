package Repositorio;
import org.springframework.stereotype.Repository;
import Controlador.Modelos.Entidades.Hecho;

import java.util.*;

@Repository
public class HechoRepositorio {
    private final Map<String, Hecho> hechos = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Hecho> getHechos() {
        return new ArrayList<>(hechos.values());
    }

}
