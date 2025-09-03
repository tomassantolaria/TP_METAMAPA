import org.springframework.stereotype.Service;
import org.simmetrics.metrics.JaroWinkler;
import org.simmetrics.StringMetric;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Service
public class Servicio {

    private final List<String> categoriaConocidas = new ArrayList<>();  //aca me voy guardando las categorias que ya entraron
    private final StringMetric metric = new JaroWinkler();
    private final float UMBRAL = 0.85f;


    public String normalizarCategoria(String categoriaEntrada){

        String categoriaNormalizada = preprocesar(categoriaEntrada);

        String mejorCategoria = null;
        float mejorSimilitud = 0.0f;


        for (String existente : categoriaConocidas){
            float similitud = metric.compare(categoriaNormalizada, existente);
            if (similitud > mejorSimilitud){
                mejorCategoria = existente;
                mejorSimilitud = similitud;
            }
        }

        if (mejorSimilitud >= UMBRAL){
            return mejorCategoria; //usamos ya una categoria existente
        } else {
            categoriaConocidas.add(categoriaNormalizada);
            return categoriaNormalizada;
        }

    }

    private String preprocesar(String texto){
        String lower = texto.toLowerCase();
        return Normalizer.normalize(lower, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    public static LocalDate normalizarFecha(String fechaEntrada){

        String fecha = fechaEntrada.trim().replace("-", "/");

        // Lista de posibles formatos
        String[] formatos = new String[]{
                "dd/MM/yyyy", "d/M/yyyy",          // formato argentino
                "yyyy/MM/dd", "yyyy/M/d",          // formato ISO
                "dd MMM yyyy", "d MMM yyyy",       // ej: 29 Aug 2025
                "MMMM d, yyyy",                     // ej: August 29, 2025
        };

        for (String pattern : formatos) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                return LocalDate.parse(fecha, formatter);
            } catch (DateTimeParseException e) {
                // probar con otro tiene que quedar vacio sino salta error
            }
        }

        // Si ningún formato de los que NOSOTROS eatablecimos como posibles funcionó, retornamos null
        return null;
    }


}

