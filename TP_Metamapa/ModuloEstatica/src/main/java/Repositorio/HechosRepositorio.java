package Repositorio;

import lombok.Getter;
import lombok.Setter;
import Modelos.Entidades.*;

import java.util.*;
@Getter
@Setter
public class HechosRepositorio {
    private final ArrayList<Hecho> hechos = new ArrayList<>();

    public void addHecho(Hecho hecho) {
        hechos.add(hecho);
    }

    public ArrayList<Hecho> allHecho() {
        return hechos;
    }

}
