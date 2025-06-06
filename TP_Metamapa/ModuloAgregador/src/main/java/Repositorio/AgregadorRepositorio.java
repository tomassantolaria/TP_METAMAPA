package Repositorio;

import Controlador.Colecciones.Coleccion;
import Controlador.Contribuyente;
import Controlador.Hecho;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AgregadorRepositorio {
    private final List<Contribuyente> contribuyentes = new ArrayList<>();


    public List<Contribuyente> getContribuyentes() {return contribuyentes;}


    public List<Hecho> getHechos(long id) {
        Coleccion coleccion = this.getColeccion(id);
        return coleccion.getHechos();
    }
}
