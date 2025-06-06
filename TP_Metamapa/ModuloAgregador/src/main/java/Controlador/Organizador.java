package Controlador;

import Controlador.Colecciones.Coleccion;
import Controlador.Filtros.Filtro;
import Controlador.Filtros.FiltroCategoria;
import Controlador.Filtros.FiltroContenidoMultimedia;
import Controlador.Filtros.FiltroTitulo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        List<Filtro> finalFiltros = filtros;
        return hechos.stream().filter(h -> finalFiltros.stream().allMatch(f->f.cumple(h, criterios))).collect(Collectors.toList());
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

