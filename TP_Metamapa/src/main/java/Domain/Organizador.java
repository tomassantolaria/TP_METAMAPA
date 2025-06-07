package Domain;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Domain.Colecciones.Coleccion;

public class Organizador {
    //private List<Filtro> filtros;
    private Criterios criterios;
    private Coleccion coleccion;

    public Organizador(Coleccion coleccion, Criterios criterios){
        this.coleccion = coleccion;
        this.criterios = criterios;
    }

    public List<Hecho> filtrar() {
        List<Hecho> hechos = new ArrayList<>();
        hechos = coleccion.getHechos();
        List<Filtro> filtros = new ArrayList<>();
        filtros = this.crearFiltros();
        return hechos.stream().filter(h -> filtros.stream().allMatch(f->f.cumple(h, criterios))).collect(Collectors.toList());
    }

    public List<Filtro> crearFiltros(){
        List<Filtro> filtros = new ArrayList<>();
        FiltroTitulo filtroTitulo = new FiltroTitulo();
        filtros.add(filtroTitulo);
        FiltroCategoria filtroCategoria = new FiltroCategoria();
        filtros.add(filtroCategoria);
        FiltroContenidoMultimedia filtroContenidoMultimedia = new FiltroContenidoMultimedia();
        filtros.add(filtroContenidoMultimedia);
        return filtros;
    }
}
