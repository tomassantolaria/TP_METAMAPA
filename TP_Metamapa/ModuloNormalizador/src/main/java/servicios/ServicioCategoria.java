package servicios;

import Repositorio.RepositorioCategoria;
import org.springframework.stereotype.Service;
import Modelos.Categoria;

@Service
public class ServicioCategoria {

    private final RepositorioCategoria repositorioCategoria;

    public ServicioCategoria(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    public String normalizarCategoria(String nombre_categoria) {
        nombre_categoria = nombre_categoria.toUpperCase();
        Categoria categoriaNormalizada = repositorioCategoria.crearCategoria(nombre_categoria);
        return capitalizarCadaPalabra(categoriaNormalizada.getNombre_categoria());
    }

    private String capitalizarCadaPalabra(String texto) {
        if (texto == null || texto.isBlank()) return texto;

        String[] palabras = texto.trim().toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            resultado.append(Character.toUpperCase(palabra.charAt(0)))
                    .append(palabra.substring(1))
                    .append(" ");
        }

        return resultado.toString().trim();
    }
}
