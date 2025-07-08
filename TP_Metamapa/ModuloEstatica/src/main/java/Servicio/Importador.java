package Servicio;

import Modelos.Entidades.Hecho;

import java.util.List;

public interface Importador {
    List<Hecho> getHechoFromFile (String ruta) throws Exception;
}
