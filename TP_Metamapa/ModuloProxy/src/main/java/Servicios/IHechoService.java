package Servicios;

import Modelos.Entidades.Hecho;
import java.util.List;

public interface IHechoService {

    List<Hecho> obtenerHechosDeFuenteDemo(String url);
    void actualizarHechos();
    void crearHecho(Hecho hecho);
}
