package Modelos.Exceptions;

public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException() {
        super("Error: categoría no encontrada");
    }
}