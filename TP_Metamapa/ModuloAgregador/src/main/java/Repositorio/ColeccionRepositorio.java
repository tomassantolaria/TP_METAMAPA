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

    public void actualizarColeccionConsesuado(List<Hecho> hechosActualizados , UUID id) {
        colecciones.get(id).setHechosConsensuados(hechosActualizados);
    }

}
