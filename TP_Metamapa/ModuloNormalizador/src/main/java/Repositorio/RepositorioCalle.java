package Repositorio;

import Modelos.Calle;
import Modelos.Localidad;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCalle {
    private List<Calle> calles;

    public Calle crearCalle(String nombre_calle, Localidad localidad) {
        Calle calle = this.obtenerCalle(nombre_calle, localidad);
        if (calle == null) {
            calle = new Calle(nombre_calle, localidad);
            agregarCalle(calle);
        }
        return calle;
    }

    public Calle obtenerCalle(String nombre_calle, Localidad localidad) {
        return this.calles.stream()
                .filter(c->c.getLocalidad().equals(localidad))
                .filter(c->c.getNombre_calle().equals(nombre_calle))
                .findFirst()
                .orElse(null);
    }
    public void agregarCalle(Calle calle) {
        calles.add(calle);
    }

}
