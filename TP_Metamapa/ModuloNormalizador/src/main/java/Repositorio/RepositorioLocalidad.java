package Repositorio;

import Modelos.Localidad;
import Modelos.Provincia;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLocalidad {
    private List<Localidad> localidades;

    public RepositorioLocalidad(){
        this.localidades = new ArrayList<>();
    }

    public Localidad crearLocalidad(String nombre_localidad, Provincia provincia) {
        Localidad localidad = this.obtenerLocalidad(nombre_localidad, provincia);
        if (localidad == null) {
            localidad = new Localidad(nombre_localidad, provincia);
            agregarLocalidad(localidad);
        }
        return localidad;
    }

    public Localidad obtenerLocalidad(String nombre_localidad, Provincia provincia) {
        return this.localidades.stream()
                .filter(c->c.getProvincia().equals(provincia))
                .filter(c->c.getNombre_localidad().equals(nombre_localidad))
                .findFirst()
                .orElse(null);
    }

    public void agregarLocalidad(Localidad localidad) {
        localidades.add(localidad);
    }

}
