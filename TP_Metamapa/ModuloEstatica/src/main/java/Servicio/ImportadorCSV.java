package Servicio;

import Modelos.DTOS.HechoDTO;
import Modelos.Entidades.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportadorCSV implements Importador{

    @Override
    public List<Hecho> getHechoFromFile(String ruta) throws Exception{
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
        for (HechoCSV hecho : hechos.getHechos()) {
            hechosRepo.add(convertToHecho(hecho));
        }
        return hechosRepo;
    }

    private Hecho convertToHecho(HechoCSV hechoCSV) {
        return new Hecho(hechoCSV.getTitulo(), hechoCSV.getDescripcion(), hechoCSV.getCategoria(), hechoCSV.getFechaAcontecimiento(), hechoCSV.getLatitud(),  hechoCSV.getLongitud());
    }
}
