package Servicio;
import Modelos.Entidades.Hecho;
import Modelos.Entidades.HechoCSV;
import Modelos.DTOS.HechoDTO;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import Modelos.Entidades.HechosCSV;
import Repositorio.HechosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service
public class FuenteEstatica {

    private HechosRepositorio repositorio;

    private static FuenteEstatica instance;
    private File carpeta = new File("ArchivosCSV");
    private Importador importador = new ImportadorCSV();

    private FuenteEstatica(File carpeta) {
        this.carpeta = carpeta;
    }

    public static FuenteEstatica getInstance(File carpeta) {
        if (instance == null) {
            instance = new FuenteEstatica(carpeta);
        }
        return instance;
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

    public void cargarHechos() {
        List<String> paths = getPaths();
        if (paths != null) {
            for (String path : paths) {
                try {
                    if (path.endsWith(".csv")) {
                        setImportador(new ImportadorCSV());
                        for(Hecho hecho : importador.getHechoFromFile(path) ) {
                            repositorio.addHecho(hecho);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Manejo básico de errores
                }
            }
        }
    } // guarda a los hechos de los archivos en el repositorio


    public List<HechoDTO> getHechos () {
        List<HechoDTO> hechosDTO = new ArrayList<>();
        List<Hecho> hechos = repositorio.allHecho();
        for (Hecho hecho : hechos ) {
            hechosDTO.add(convertToDTO(hecho));
        }
        return hechosDTO;
    }

    private HechoDTO convertToDTO(Hecho hecho) {
        return new HechoDTO(hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getFechaAcontecimiento(), hecho.getLatitud(),  hecho.getLongitud());
    }
}

