package Repositorio;

import Modelos.Entidades.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ColeccionRepositorio{
    //Optional<Coleccion> obtenerPorId(Long id);
    private final Map<UUID, Coleccion> colecciones = new HashMap<>();

    public Coleccion obtenerPorId(UUID id){
        return colecciones.get(id);
    }


}
