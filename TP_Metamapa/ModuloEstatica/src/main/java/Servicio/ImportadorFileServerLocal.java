package Servicio;

import Modelos.Entidades.*;
import Repositorio.ArchivosRepositorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportadorFileServerLocal implements Importador{
    private File carpeta = new File("ArchivosCSV");
    private ArchivosRepositorio archRepo = new ArchivosRepositorio();
    @Override
    public List<HechoCSV> getHechoFromFile(String ruta) throws Exception{
        HechosCSV hechos = new HechosCSV();
        List<Hecho> hechosRepo= new ArrayList<>();
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
        return hechos.getHechos();
    }
     @Override
    public List<String> getPaths() throws Exception {
        List<String> paths = new ArrayList<>();
            if(carpeta.exists() && carpeta.isDirectory()) {
                if(carpeta.listFiles() != null) {
                    for (File archivo : carpeta.listFiles()) {
                        if (archivo.isFile()) { // solo archivos, no subdirectorios
                            paths.add(archivo.getAbsolutePath());
                        }
                    }
                }
            } else {
                throw new Exception("No existe la carpeta o esta vacia");
            }
            return paths;
    }

    private Hecho convertToHecho(HechoCSV hechoCSV) {
        return new Hecho(hechoCSV.getTitulo(), hechoCSV.getDescripcion(), hechoCSV.getFuente(), hechoCSV.getCategoria(), hechoCSV.getFechaAcontecimiento(), hechoCSV.getLatitud(),  hechoCSV.getLongitud(), false);
    }
}
