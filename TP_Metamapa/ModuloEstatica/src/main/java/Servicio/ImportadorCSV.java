package Servicio;

import Modelos.DTOS.HechoDTO;
import Modelos.Entidades.HechoCSV;
import Modelos.Entidades.HechosCSV;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportadorCSV implements Importador{

    @Override
    public List<HechoDTO> getHechoFromFile(String ruta) throws Exception{
        HechosCSV hechos = new HechosCSV();
        List<HechoDTO> hechosDTO = new ArrayList<>();
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
            hechosDTO.add(convertToDTO(hecho));
        }
        return hechosDTO;
    }

    public HechoDTO convertToDTO(HechoCSV hechoCSV) {
        return new HechoDTO(hechoCSV.getTitulo(), hechoCSV.getDescripcion(), hechoCSV.getCategoria(), hechoCSV.getLatitud(),  hechoCSV.getLongitud(), hechoCSV.getFechaAcontecimiento());
    }
}
