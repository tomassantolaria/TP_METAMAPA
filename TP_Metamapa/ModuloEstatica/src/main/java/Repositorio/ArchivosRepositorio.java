package Repositorio;

import Modelos.Entidades.Archivo;
import Modelos.Entidades.Hecho;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
@Setter
public class ArchivosRepositorio {
    private final List<Archivo> hechos = new ArrayList<>();

    public Long agregarPath(String path) {
        //TODO: crear un nuevo archivo con id autoincremental
        //new Archivo(path) --> lo hace JPA
        return null; // devuelve el ID autoincremental

    }


    public Long existePath(String path) {
        //TODO : hacer consulta de si existe ese path, si es null es que no existe
        return null;
    }
}
