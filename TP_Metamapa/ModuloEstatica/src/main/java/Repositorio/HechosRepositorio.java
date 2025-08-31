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

    public void addAllHechos(List<Hecho> hechosNuevos) {
        hechos.addAll(hechosNuevos);
    }

    public ArrayList<Hecho> allHecho() {
        return hechos;
    }

    public List<Hecho> allHechosNoEnviados() {
        List<Hecho> hechosNoEnviados = new ArrayList<>();
        for (Hecho hecho : allHecho()) {
            if(!hecho.getProcesado()) {
                hecho.setProcesado(true);
                hechosNoEnviados.add(hecho);
            }
        }
        return hechosNoEnviados;
    }
    public Boolean noExisteHecho(HechoCSV hecho, Long id) {
        // TODO : hacer que busque un hecho que sea igual, si existe en la base de datos, entonces false)
        return true;
    }

    public Archivo existePath(String path) {
        //TODO : hacer consulta de si existe ese path, si es null es que no existe
        return null;
    }

}
