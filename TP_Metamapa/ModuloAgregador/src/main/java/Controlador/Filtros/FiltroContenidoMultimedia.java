package Controlador.Filtros;

import Controlador.ContenidoMultimedia;
import Controlador.Criterios;
import Controlador.Hecho;


public class FiltroContenidoMultimedia implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        ContenidoMultimedia contenido_multimedia = criterios.getContenido().getContenidoMultimedia();
        return contenido_multimedia == null || unHecho.obtenerContenidoMultimedia() == contenido_multimedia ;
    }
}