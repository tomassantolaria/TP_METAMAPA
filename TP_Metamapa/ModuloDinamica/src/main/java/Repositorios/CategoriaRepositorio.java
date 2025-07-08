package Repositorios;

import java.util.*;
import Modelos.Entidades.*;

public class CategoriaRepositorio {
    private List<Categoria> categorias;

    public CategoriaRepositorio(){
        this.categorias = new ArrayList<>();
    }

    public Categoria crearCategoria(Categoria categoria) {
        Categoria categoriaNueva = this.obtenerCategoria(categoria);
        if (categoria == null) {
            categoria = new Categoria(categoriaNueva.getNombre());
            agregarCategoria(categoria);
        }
        return categoria;
    }

    public Categoria obtenerCategoria(Categoria categoria){
        return this.categorias.stream()
                .filter(c->c.getNombre().equals(categoria.getNombre()))
                .findFirst()
                .orElse(null);
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
}
