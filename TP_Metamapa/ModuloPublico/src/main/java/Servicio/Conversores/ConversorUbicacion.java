package Servicio.Conversores;
import Modelos.Entidades.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class ConversorUbicacion {

    public static Ubicacion convert(String nombre, Double latitud, Double longitud) {
        return new Ubicacion(nombre, latitud, longitud);
    }
}