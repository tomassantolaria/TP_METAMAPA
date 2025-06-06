package Repositorio;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import Modelos.Entidades.*;


@Repository
public class HechoRepository {
    private final List<Hecho> hechos = new ArrayList<>();

    public void guardarHecho(Hecho hecho) {
        hechos.add(hecho);
    }

    public List<Hecho> obtenerTodos() {
        return hechos;
    }


}
