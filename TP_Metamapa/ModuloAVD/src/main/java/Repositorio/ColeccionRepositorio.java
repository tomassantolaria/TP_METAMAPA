package Repositorio;

import Modelos.Entidades.*;
import org.springframework.stereotype.Repository;
import Servicio.Consenso.*;

import java.util.*;

@Repository
public class ColeccionRepositorio{
    //Optional<Coleccion> obtenerPorId(Long id);
    private final Map<UUID, Coleccion> colecciones = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public ArrayList<Coleccion> getTodas() {
        return new ArrayList<>(colecciones.values());
    }

    public Coleccion obtenerPorId(UUID id){
        return colecciones.get(id);
    }

    public void agregar(Coleccion coleccion){colecciones.put(coleccion.getId(),coleccion);}

    public void eliminarHecho(UUID id) {
        for (Coleccion coleccion : colecciones.values()) {
            coleccion.getHechos().removeIf(hecho -> hecho.getId().equals(id));
        }
    }



    public void modificarConsenso(UUID id, Consenso conseso) {
        colecciones.get(id).setConsenso(conseso);
    }

    public void eliminarColeccion(UUID id) {
        colecciones.remove(id);
    }

    public void eliminarHechosFuente(UUID id, UUID fuente) {
        Coleccion coleccion = colecciones.get(id);
        if (coleccion == null) {
            throw new IllegalArgumentException("No existe esa coleccion");
        }
        if (coleccion.getHechos() == null || coleccion.getHechos().isEmpty() ) {
            throw new IllegalArgumentException("Coleccion vacia");
        }
        for (Hecho hecho : coleccion.getHechos()){
            if(hecho.getIdFuente().equals(fuente)) {
                coleccion.eliminarHecho(hecho);
            }
        }
    }
}
