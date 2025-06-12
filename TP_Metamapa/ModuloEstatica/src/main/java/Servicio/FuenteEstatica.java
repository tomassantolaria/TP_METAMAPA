package Servicio;
import Modelos.Entidades.HechoCSV;
import Modelos.DTOS.HechoDTO;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import Modelos.Entidades.HechosCSV;
import org.springframework.stereotype.Service;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service
public class FuenteEstatica {

    private File carpeta = new File("ArchivosCSV");
    private Importador importador = new ImportadorCSV();

    public Importador getImportador() {
        return importador;
    }

    public File getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(File carpeta) {
        this.carpeta = carpeta;
    }

    public void setImportador(Importador importador) {
        this.importador = importador;
    }

    private List<String> getPaths() {
            List<String> paths = new ArrayList<>();
            if(carpeta.exists() && carpeta.isDirectory()) {
                if(carpeta.listFiles() != null) {
                    for (File archivo : carpeta.listFiles()) {
                        if (archivo.isFile()) { // solo archivos, no subdirectorios
                            paths.add(archivo.getAbsolutePath());
                        }
                    }
                }

            } else{
                System.out.println("La carpeta no existe o no es un directorio");
            }
            return paths;
    }



    public List<HechoDTO> getHechos () {
        List<HechoDTO> hechosDTO = new ArrayList<>();
        List<String> paths = getPaths();
        if (paths != null) {
            for (String path : paths) {
                try {
                    if (path.endsWith(".csv")) {
                        setImportador(new ImportadorCSV());
                        hechosDTO = importador.getHechoFromFile(path);
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Manejo básico de errores
                }
            }
        }
        return hechosDTO;
    }
}

