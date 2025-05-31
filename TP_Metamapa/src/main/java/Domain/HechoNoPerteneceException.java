package Domain;

public class HechoNoPerteneceException extends RuntimeException {
    public HechoNoPerteneceException(String mensaje) {
        super(mensaje);
    }
}