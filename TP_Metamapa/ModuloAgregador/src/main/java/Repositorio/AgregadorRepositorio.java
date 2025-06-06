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

    public void agregarContribuyente(Contribuyente contribuyente) {
        contribuyentes.add(contribuyente);
    }

    public List<Contribuyente> getContribuyentes() {return contribuyentes;}

    private final List<Coleccion> colecciones = new ArrayList<>();
    public void agregarColeccion(Coleccion coleccion) {colecciones.add(coleccion);}

    public List<Coleccion> getColecciones() {return colecciones;}

    public Coleccion getColeccion(long id) {return colecciones.get(id);}

    public List<Hecho> getHechos(long id) {
        Coleccion coleccion = this.getColeccion(id);
        return coleccion.getHechos();}
}
