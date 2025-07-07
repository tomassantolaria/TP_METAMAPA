package Modelos.Entidades;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.stereotype.Service;

@Service
public class ConversorCategoria {
    public static Categoria convert(String nombre) {
        return Categoria.getInstance(nombre);
    }
}



