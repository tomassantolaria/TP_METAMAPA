package Modelos.Repositorios;

import Modelos.Entidades.Hecho;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class HechosRepositorio implements IHechosRepositorio {
    private List<Hecho> hechoslista;


    @Override
    public void guardarHecho(Hecho hecho) {

    }

    @Override
    public List<Hecho> obtenerHechos() {
        return List.of();
    }

    @Override
    public void eliminarHecho(Hecho hecho) {

    }
}
