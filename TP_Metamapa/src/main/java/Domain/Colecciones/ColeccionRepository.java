package Domain.Colecciones;

import Domain.Categoria;
import Domain.Colecciones.Coleccion;

import java.util.*;

public class ColeccionRepository {
    //Optional<Coleccion> obtenerPorId(Long id);
    private final Map<String, Coleccion> colecciones = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Coleccion> getTodas() {
        return new ArrayList<>(colecciones.values());
    }

    public Coleccion obtenerPorId(Long id){
        return colecciones.get(id);
    }

}
