package Servicio.Conversores;

import Modelos.Entidades.Categoria;
import org.springframework.stereotype.Service;

@Service
public class ConversorCategoria {
    public static Categoria convert(String nombre) {
        return Categoria.getInstance(nombre);
    }
}



