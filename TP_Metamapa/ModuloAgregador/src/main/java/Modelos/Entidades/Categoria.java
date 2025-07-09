package Modelos.Entidades;
import Repositorio.CategoriaRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria{
    private String nombre;
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public static Categoria getInstance(String nombre) {return CategoriaRepositorio.crearCategoria(nombre);}
}

