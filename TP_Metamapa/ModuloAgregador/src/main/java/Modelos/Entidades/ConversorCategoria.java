package Modelos.Entidades;

import com.fasterxml.jackson.databind.util.StdConverter;


public class ConversorCategoria extends StdConverter<String, Categoria> {
    public Categoria convert(String nombre) {
        return Categoria.getInstance(nombre);
    }
}



