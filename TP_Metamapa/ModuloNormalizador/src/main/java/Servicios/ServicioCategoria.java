package Servicios;

import Repositorio.RepositorioCategoria;
import org.springframework.stereotype.Service;
import Modelos.Categoria;

@Service
public class ServicioCategoria {
    private final RepositorioCategoria repositorioCategoria;

    public ServicioCategoria(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    public Categoria normalizarCategoria(String nombre_categoria) {
        nombre_categoria = nombre_categoria.toUpperCase();
        return repositorioCategoria.crearCategoria(nombre_categoria);
    }
}
