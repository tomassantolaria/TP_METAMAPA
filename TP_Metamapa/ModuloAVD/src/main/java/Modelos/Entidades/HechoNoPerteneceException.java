package Modelos.Entidades;

public class HechoNoPerteneceException extends RuntimeException {

    public HechoNoPerteneceException() {
        super("El hecho no pertenece a la colección.");
    }

}
