package Modelos.Entidades;
public class HechoYaExisteException extends RuntimeException {

    public HechoYaExisteException() {
        super("El hecho ya existe en la colección.");
    }

    public HechoYaExisteException(String message) {
        super(message);
    }
}

