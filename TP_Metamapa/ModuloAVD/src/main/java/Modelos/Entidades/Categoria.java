package Modelos.Entidades;
import Repositorio.CategoriaRepositorio;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service

public class Categoria {

    private static CategoriaRepositorio repositorio;

    private String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public static Categoria getInstance(String nombre) {
        return repositorio.crearCategoria(nombre);
    }

    @Autowired
    public void setRepositorio(CategoriaRepositorio repo) {
        Categoria.repositorio = repo;
    }
}

