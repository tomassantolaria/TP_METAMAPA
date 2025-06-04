package Domain;

import java.util.*;

public class CategoriaRepository {
    private final Map<String, Categoria> categorias = new HashMap<>();

    // Constructor con algunas precargadas opcionalmente
    public CategoriaRepository() {
        //agregarCategoria(new Categoria("Historia"));
        //agregarCategoria(new Categoria("Ciencia"));
        //agregarCategoria(new Categoria("Arte"));
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.put(categoria.getNombre().toUpperCase(), categoria);
    }

    // ✅ Devuelve una existente o crea una nueva si no está
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
