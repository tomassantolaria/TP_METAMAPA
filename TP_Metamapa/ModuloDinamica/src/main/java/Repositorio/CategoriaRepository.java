package Repositorio;

import java.util.*;
import Modelos.Entidades.*;

public class CategoriaRepository {
    private final Map<String, Categoria> categorias = new HashMap<>();

    public CategoriaRepository() {
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.put(categoria.getNombre().toUpperCase(), categoria);
    }

    public Categoria obtenerOCrearPorNombre(String nombre) {
        String clave = nombre.toUpperCase();
        Categoria categoria = categorias.get(clave);
        if (categoria == null) {
            categoria = new Categoria(nombre);
            agregarCategoria(categoria);
        }
        return categoria;
    }

    public List<Categoria> getTodas() {
        return new ArrayList<>(categorias.values());
    }
}
