package Servicio;

import Modelos.DTOS.HechoDTO;

import java.util.List;

public interface Importador {
    List<HechoDTO> getHechoFromFile (String ruta) throws Exception;
}
