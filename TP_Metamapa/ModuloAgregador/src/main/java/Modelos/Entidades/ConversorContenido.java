package Modelos.Entidades;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.stereotype.Service;

@Service
public class ConversorContenido {

    public static Contenido convert(String texto, String multimedia) {
        return new Contenido(texto, multimedia);
    }
}
