package Modelos.Exceptions;

public class ColeccionNoEncontradaException extends RuntimeException {
    public ColeccionNoEncontradaException() {
        super("Error: colección no encontrada");
    }
}
