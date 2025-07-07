package Modelos.Entidades;

import com.fasterxml.jackson.databind.util.StdConverter;


public class ConversorContenido extends StdConverted<String, String, Contenido> {
    public Contenido convert(String texto, String multimedia) {
        return new Contenido(texto, multimedia);
    }
}

