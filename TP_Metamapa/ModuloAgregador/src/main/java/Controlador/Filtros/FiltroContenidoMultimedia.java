package Controlador.Filtros;

import Controlador.Modelos.Entidades.ContenidoMultimedia;
import Controlador.Modelos.Entidades.Criterios;
import Controlador.Modelos.Entidades.Hecho;


public class FiltroContenidoMultimedia implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        ContenidoMultimedia contenido_multimedia = criterios.getContenido().getContenidoMultimedia();
        return contenido_multimedia == null || unHecho.getContenido().getContenidoMultimedia() == contenido_multimedia ;
    }
}