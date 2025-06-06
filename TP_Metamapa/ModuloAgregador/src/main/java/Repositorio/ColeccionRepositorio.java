package Repositorio;

import Controlador.Colecciones.Coleccion;
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

}
