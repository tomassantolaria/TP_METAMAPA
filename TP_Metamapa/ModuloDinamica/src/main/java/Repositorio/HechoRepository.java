package Repositorio;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import Modelos.Entidades.*;


@Repository
public class HechoRepository {
    private List<Hecho> hechos;

    public HechoRepository(){
        this.hechos = new ArrayList<>();
    }

    public void guardarHecho(Hecho hecho) {
        hechos.add(hecho);
    }

    public Hecho buscarHechoPorId(String id){
        return this.hechos.stream()
                .filter(h->h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
