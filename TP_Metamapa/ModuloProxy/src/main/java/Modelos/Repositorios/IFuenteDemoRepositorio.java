package Modelos.Repositorios;

import Modelos.Entidades.Hecho;
import java.util.List;

public interface IFuenteDemoRepositorio {
    Hecho guardarHecho(Hecho hecho);
    List<Hecho> obtenerHechos();
    void eliminarHecho(Hecho hecho);
}
