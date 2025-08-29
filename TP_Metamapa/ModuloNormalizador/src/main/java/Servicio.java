import Repositorio.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.simetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;
import java.util.ArrayList;
import java.util.List;

@Service
public class Servicio {
    @Autowired
//    private RepositorioCategoria repositorioCategoria;
//    public String normalizarCategoria(String categoria) {
//        String categoria_normalizada = categoria.trim().toLowerCase();
//        // Aquí podrías usar el repositorio para guardar o verificar la categoría
//        // repositorioCategoria.guardar(categoria_normalizada);
//        return categoria_normalizada;
//    }

    private final List<String> categoriaConocidas = new ArrayList<>();  //aca me voy guardando las categorias que ya entraron
    private final StringMetric metric = StringMetrics.jaroWinkler();
    private final float UMBRAL = 0.85f;


    public String normalizarCategoria(String categoriaEntrada){

    }


}

