package Modelos.Entidades.Excepciones;

public class ColeccionNotFoundException extends RuntimeException {
    public ColeccionNotFoundException(String message) {
        super(message);
    }
}
