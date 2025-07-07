package Modelos.Entidades;
import org.springframework.stereotype.Service;

@Service
public class ConversorUbicacion {

    public static Ubicacion convert(String nombre, Double latitud, Double longitud) {
        return new Ubicacion(nombre, latitud, longitud);
    }
}