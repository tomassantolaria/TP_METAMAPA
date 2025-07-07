package Repositorio;

import Modelos.Entidades.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorio {
    private List<Categoria> categorias;

    public CategoriaRepositorio(){
        this.categorias = new ArrayList<>();
    }

    public Categoria crearCategoria(String nombre) {
        Categoria categoria = this.obtenerCategoria(nombre);
        if (categoria == null) {
            categoria = new Categoria(nombre);
            agregarCategoria(categoria);
        }
        return categoria;
    }

    public Categoria obtenerCategoria(String nombre){
        return this.categorias.stream()
                .filter(c->c.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
}
