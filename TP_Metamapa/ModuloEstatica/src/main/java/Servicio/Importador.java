package Servicio;

import Modelos.Entidades.Hecho;
import Modelos.Entidades.HechoCSV;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public interface Importador {
    List<HechoCSV> getHechoFromFile (String ruta) throws Exception;
    List<String> getPaths() throws Exception;
    void guardarCSV(String originalFilename, MultipartFile file) throws Exception;
}
