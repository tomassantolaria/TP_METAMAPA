package Servicios;

import Modelos.Entidades.Hecho;
import java.util.List;

public interface IFuenteDemoService {

    List<Hecho> obtenerHechos(String url);
    void actualizarHechos();
}
