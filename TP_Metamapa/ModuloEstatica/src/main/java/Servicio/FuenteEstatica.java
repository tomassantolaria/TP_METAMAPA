package Servicio;
import Modelos.HechoCSV;
import Modelos.HechoDTO;

import java.lang.reflect.Array;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import Modelos.HechosCSV;
import org.springframework.stereotype.Service;
import lombok.Value;
import java.util.Arrays;
import java.io.File;


@Service
public class FuenteEstatica {

    private File carpeta = new File("ArchivosCSV");

    public List<String> getPaths() {
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
                    HechosCSV hechosCSV = getHechoCSV(path);
                    for (HechoCSV hecho : hechosCSV.getHechos()) {
                        hechosDTO.add(convertToDTO(hecho));
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Manejo básico de errores
                }
            }
        }
        return hechosDTO;
    }
    public HechoDTO convertToDTO(HechoCSV hechoCSV) {
        return new HechoDTO(hechoCSV.getTitulo(), hechoCSV.getDescripcion(), hechoCSV.getCategoria(), hechoCSV.getLatitud(),  hechoCSV.getLongitud(), hechoCSV.getFechaAcontecimiento());
    }

    public HechosCSV getHechoCSV (String ruta) throws Exception {

            HechosCSV hechos = new HechosCSV();
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            br.readLine(); // Saltar encabezado
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                HechoCSV hecho = HechoCSV.getInstance(
                        campos[0], // Título
                        campos[1], // Descripción
                        campos[2], // Categoría
                        LocalDate.parse(campos[5], formatter), // Fecha del hecho
                        Double.parseDouble(campos[3]), // Latitud
                        Double.parseDouble(campos[4])// Longitud
                );

                hechos.addHecho(hecho);
            }

            br.close();

            return hechos;
    }
}



