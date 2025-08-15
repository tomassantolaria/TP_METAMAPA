package Repositorio;

import Modelos.Entidades.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepositorio {
    private static List<Categoria> categorias;

    public CategoriaRepositorio(){
        categorias = new ArrayList<>();
    }

    public static Categoria crearCategoria(String nombre) {
        Categoria categoria = obtenerCategoria(nombre);
        if (categoria == null) {
            categoria = new Categoria(nombre);
            agregarCategoria(categoria);
        }
        return categoria;
    }

    public static Categoria obtenerCategoria(String nombre){
        return categorias.stream()
                .filter(c->c.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    public static void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
}
