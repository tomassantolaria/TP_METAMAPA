package Servicio;
import Modelos.Entidades.Hecho;
import Modelos.DTOS.HechoDTO;
import java.util.List;
import java.util.ArrayList;
import Repositorio.HechosRepositorio;
import org.springframework.stereotype.Service;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service
public class FuenteEstatica {

    private HechosRepositorio repositorio;
    private List<String> filesProcesados;
    private static FuenteEstatica instance;
//    private File carpeta = new File("ArchivosCSV");
    private Importador importador = new ImportadorFileServerLocal();

    private FuenteEstatica() {
    }

    public static FuenteEstatica getInstance() {
        if (instance == null) {
            instance = new FuenteEstatica();
        }
        return instance;
    }

    public void cargarHechos() {
        try {
            List<String> paths = importador.getFiles();
            List<Hecho> hechosRepo;
            paths = paths.stream().filter(path -> !filesProcesados.contains(path)).toList();
            for (String path : paths) {
                hechosRepo = importador.getHechoFromFile(path);
                repositorio.addAllHechos(hechosRepo);
            }
            filesProcesados.addAll(paths);
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // guarda a los hechos de los archivos en el repositorio


    public List<HechoDTO> getHechos () {
        List<HechoDTO> hechosDTO = new ArrayList<>();
        List<Hecho> hechos = repositorio.allHechosNoEnviados();
        for (Hecho hecho : hechos ) {
            hechosDTO.add(convertToDTO(hecho));
        }
        return hechosDTO;
    }

    private HechoDTO convertToDTO(Hecho hecho) {
        return new HechoDTO(hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getFechaAcontecimiento(), hecho.getLatitud(),  hecho.getLongitud());
    }
}

