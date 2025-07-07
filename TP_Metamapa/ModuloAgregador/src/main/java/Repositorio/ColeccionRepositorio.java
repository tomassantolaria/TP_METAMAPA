package Repositorio;

import Modelos.Entidades.Coleccion;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ColeccionRepositorio{
    //Optional<Coleccion> obtenerPorId(Long id);
    private final Map<String, Coleccion> colecciones = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Coleccion> getTodas() {
        return new ArrayList<>(colecciones.values());
    }

    public Coleccion obtenerPorId(String id){
        return colecciones.get(id);
    }

    public void agregar(Coleccion coleccion){colecciones.put(coleccion.getId(),coleccion);}

    public void eliminarHecho(String id) {
        for (Coleccion coleccion : colecciones.values()) {
            coleccion.getHechos().removeIf(hecho -> hecho.getId().equals(id));
        }
    }
}
