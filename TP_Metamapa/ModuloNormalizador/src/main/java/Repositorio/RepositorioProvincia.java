package Repositorio;

import Modelos.Calle;
import Modelos.Provincia;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProvincia {
    private List<Provincia> provincias;

    public Provincia crearProvincia(String nombre) {
        Provincia provincia = this.obtenerProvincia(nombre);
        if (provincia == null) {
            provincia = new Provincia(nombre);
            agregarProvincia(provincia);
        }
        return provincia;
    }

    public Provincia obtenerProvincia(String nombre){
        return this.provincias.stream()
                .filter(c->c.getNombre_provincia().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    public void agregarProvincia(Provincia provincia) {
        provincias.add(provincia);
    }
}
