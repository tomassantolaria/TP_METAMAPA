package Servicio;
import Modelos.Entidades.Hecho;
import Modelos.DTOS.HechoDTO;
import Modelos.Entidades.Archivo;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import Modelos.Entidades.HechoCSV;
import Repositorio.HechosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service
public class FuenteEstatica {

    @Autowired
    HechosRepositorio repositorio;
    private Date ultimaFechaCarga;
    private static FuenteEstatica instance;
    private Importador importador = new ImportadorFileServerLocal();

    private FuenteEstatica() {
    }

    public static FuenteEstatica getInstance() {
        if (instance == null) {
            instance = new FuenteEstatica();
        }
        return instance;
    }

    public void cargarHechos() throws  Exception {
        try {
            List<String> paths = importador.getPaths();
            List<HechoCSV> hechosCSV ;
            paths = paths.stream().filter(path -> esPosteriorAUltimaCarga(path)).toList();
            for (String path : paths) {
                hechosCSV = importador.getHechoFromFile(path);
                Archivo archivo = repositorio.existePath(path);
                if(archivo == null)
                {
                    Archivo nuevoArchivo = new Archivo(path);
                    guardarHechos(hechosCSV,nuevoArchivo);
                }
                else {
                    hechosCSV = hechosCSV.stream().filter(hecho -> repositorio.noExisteHecho( archivo.getId(), hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getLatitud(), hecho.getLongitud(), hecho.getFechaAcontecimiento())).toList();
                    guardarHechos(hechosCSV,archivo);
                }
            }
            ultimaFechaCarga = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // guarda a los hechos de los archivos en el repositorio


    private Boolean esPosteriorAUltimaCarga(String path){
        File archivo = new File(path);
        if (archivo.exists()) {
            Date fechaModificacion = new Date(archivo.lastModified());
            return fechaModificacion.after(ultimaFechaCarga);
        }
        else {
           System.out.println("Archivo no encontrado");
           return false;
        }
    }

    @Transactional
    public List<Hecho> obtenerNoEnviados() {
        List<Hecho> hechosNoEnviados = repositorio.findAllByProcesadoFalse();
        hechosNoEnviados.forEach(h -> h.setProcesado(true));
        return hechosNoEnviados;
    }

    public List<HechoDTO> getHechosNoEnviados() {
        List<HechoDTO> hechosDTO = new ArrayList<>();
        List<Hecho> hechos = obtenerNoEnviados();
        for (Hecho hecho : hechos ) {
            hechosDTO.add(convertToDTO(hecho));
        }
        return hechosDTO;
    }

    private HechoDTO convertToDTO(Hecho hecho) {
        return new HechoDTO(hecho.getTitulo(), hecho.getDescripcion(), hecho.getArchivo().getId(),hecho.getCategoria(), hecho.getFechaAcontecimiento(), hecho.getLatitud(),  hecho.getLongitud());
    }
    private void guardarHechos(List<HechoCSV> hechosCSV, Archivo archivo) {
        for (HechoCSV hechoCSV : hechosCSV) {
            Hecho hecho = convertToHecho(hechoCSV, archivo);
            // AGREGUE VERIFICACION DE FECHA !!!!!
            if (hecho.getFechaAcontecimiento().isBefore(LocalDate.now()) || hecho.getFechaAcontecimiento().isEqual(LocalDate.now())) {
                repositorio.save(hecho); // esto era un addHecho(Hecho)
            }
        }
    }
    private Hecho convertToHecho(HechoCSV hechoCSV, Archivo archivo) {
        return new Hecho(hechoCSV.getTitulo(), hechoCSV.getDescripcion(), archivo, hechoCSV.getCategoria(), hechoCSV.getFechaAcontecimiento(), hechoCSV.getLatitud(), hechoCSV.getLongitud(), false);
    }
}

