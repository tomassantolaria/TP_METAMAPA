package Servicio.Filtros;

import Modelos.Entidades.ContenidoMultimedia;
import Modelos.Entidades.Criterios;
import Modelos.Entidades.Hecho;

import org.springframework.stereotype.Service;

@Service
public class FiltroContenidoMultimedia implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, String contenido_multimedia) {

        return contenido_multimedia == null || unHecho.getContenido().getContenidoMultimedia().toString().equalsIgnoreCase(contenido_multimedia) ;
    }
}