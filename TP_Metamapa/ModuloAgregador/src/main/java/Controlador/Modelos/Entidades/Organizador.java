package Controlador.Modelos.Entidades;

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
    private List<Hecho> hechos;

    public Organizador(List<Hecho> hechos, Criterios criterios){
        this.hechos = hechos;
        this.criterios = criterios;
    }

    public List<Hecho> filtrar() {
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

