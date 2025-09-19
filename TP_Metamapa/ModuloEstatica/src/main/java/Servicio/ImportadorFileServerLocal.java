package Servicio;

import Modelos.Entidades.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Component("ImportadorFileServerLocal")
@Service
public class ImportadorFileServerLocal implements Importador{
    final private File carpeta = new File("src/main/resources/datos/Fuentes_de_hechos/");
    @Override
    public List<HechoCSV> getHechoFromFile(String ruta) throws Exception {
        System.out.println("EXTRAYENDO HECHOS DE" + ruta);
        HechosCSV hechos = new HechosCSV();
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        br.readLine(); // Saltar encabezado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
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
        return hechos.getHechos();
    }
     @Override
    public List<String> getPaths() throws Exception {
        List<String> paths = new ArrayList<>();
         System.out.println("CARPETA EXISTE :"+ carpeta.exists());
         System.out.println("CARPETA es DIRECTORIO :"+ carpeta.isDirectory());
            if(carpeta.exists() && carpeta.isDirectory()) {
                System.out.println("CARPETA EXISTE Y ES DIRECTORIO");
                File[] archivos = carpeta.listFiles();
                if(archivos != null) {
                    System.out.println("tiene archivos");
                    for (File archivo : archivos){
                        if (archivo.isFile()) { // solo archivos, no subdirectorios
                            System.out.println(archivo.getName());
                            paths.add(archivo.getAbsolutePath());
                        }
                    }
                }
            } else {
                throw new Exception("No existe la carpeta o esta vacia");
            }
            return paths;
    }

}
