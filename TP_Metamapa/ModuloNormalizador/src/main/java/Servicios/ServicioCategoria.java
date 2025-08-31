package Servicios;

import Repositorio.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Modelos.Categoria;

@Service
public class ServicioCategoria {
    @Autowired
    private RepositorioCategoria repositorioCategoria;

    public Categoria normalizarCategoria(String nombre_categoria) {
        nombre_categoria = nombre_categoria.toUpperCase();
        return repositorioCategoria.crearCategoria(nombre_categoria);
    }
}
