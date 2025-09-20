package Servicio;

import Modelos.Entidades.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

            String titulo     = unquoteCsvField(campos[0]); // Título
            String descripcion= unquoteCsvField(campos[1]); // Descripción
            String categoria  = unquoteCsvField(campos[2]); // Categoría
            String lat = campos[3].trim();
            String lon = (campos[4].trim());

            HechoCSV hecho = HechoCSV.getInstance(
                    titulo,
                    descripcion,
                    categoria,
                    LocalDate.parse(campos[5].trim(), formatter),
                    lat,
                    lon
            );

            hechos.addHecho(hecho);
        }

        br.close();
        return hechos.getHechos();
    }

    private static String unquoteCsvField(String s) {



        if (s == null) return null;
        s = s.trim();
        // Quita comillas envolventes "..."
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1);
        }
        // Convierte comillas escapadas CSV ("") a una sola (")
        s = s.replace("\"\"", "\"");
        return s;
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
