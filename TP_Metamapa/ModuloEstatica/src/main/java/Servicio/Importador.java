package Servicio;

import Modelos.Entidades.Hecho;
import Modelos.Entidades.HechoCSV;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public interface Importador {
    List<HechoCSV> getHechoFromFile (String ruta) throws Exception;
    List<String> getPaths() throws Exception;
}
