package Modelos.Repositorio;

import Modelos.Entidades.Contribuyente;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ContribuyenteRepositorio {
    //Optional<Coleccion> obtenerPorId(Long id);
    private final Map<String, Contribuyente> contribuyentes = new HashMap<>();

// Constructor con algunas precargadas opcionalmente

    public ArrayList<Contribuyente> getTodos() {
        return new ArrayList<>(contribuyentes.values());
    }

    public Contribuyente obtenerPorId(String usuario) {
        return contribuyentes.get(usuario);
    }

    public void agregarContribuyente(Contribuyente contribuyente) {
        contribuyentes.put(contribuyente.getUsuario(), contribuyente);
    }
}