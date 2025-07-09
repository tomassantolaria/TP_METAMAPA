package Servicio.Conversores;

import Modelos.Entidades.Contenido;
import org.springframework.stereotype.Service;

@Service
public class ConversorContenido {

    public static Contenido convert(String texto, String multimedia) {
        return new Contenido(texto, multimedia);
    }
}
