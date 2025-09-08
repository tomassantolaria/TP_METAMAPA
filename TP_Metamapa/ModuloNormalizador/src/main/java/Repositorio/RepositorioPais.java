package Repositorio;

import Modelos.Pais;

import java.util.List;

public class RepositorioPais {
    private List<Pais> pais;

    public Pais crearPais(String nombre) {
        Pais pais = this.obtenerPais(nombre);
        if (pais == null) {
            pais = new Pais(nombre);
            agregarPais(pais);
        }
        return pais;
    }

    public Pais obtenerPais(String nombre){
        return this.pais.stream()
                .filter(c->c.getNombre_pais().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    public void agregarPais(Pais pais) {
        this.pais.add(pais);
    }
}
