package Servicio;
import Modelos.Entidades.Hecho;
import Modelos.DTOS.HechoDTO;
import Modelos.Entidades.Archivo;

import java.io.File;
import java.time.LocalDateTime ;
import java.util.List;
import java.util.ArrayList;

import Modelos.Entidades.HechoCSV;
import Repositorio.HechosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.Date;
import Repositorio.ArchivoRepository;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class FuenteEstatica {

    @Autowired
    HechosRepositorio repositorio;
    @Autowired
    ArchivoRepository archivoRepository;
    private Date ultimaFechaCarga = new Date();
    private static FuenteEstatica instance;
    private Importador importador = new ImportadorFileServerLocal();


    public FuenteEstatica(ArchivoRepository archivoRepository,
                          HechosRepositorio repositorio) {
        this.importador = importador;
        this.repositorio = repositorio;
        this.archivoRepository = archivoRepository;
    }


    public void cargarHechos() throws  Exception {
        try {
            List<String> paths = importador.getPaths();
            List<HechoCSV> hechosCSV ;

            for (String path : paths) {

                Archivo archivo = archivoRepository.findByPath(path)
                        .orElseGet(() -> {
                            return null;
                        });

                if(archivo != null &&  !esPosteriorAUltimaCarga(path)) {
                    continue;
                }
                hechosCSV = importador.getHechoFromFile(path);
                if(archivo == null)
                {
                    Archivo nuevoArchivo = new Archivo();
                    nuevoArchivo.setPath(path);
                    archivoRepository.save(nuevoArchivo);
                    guardarHechos(hechosCSV,nuevoArchivo);
                }
                else {
                    List<HechoCSV> hechosModificados = hechosCSV.stream().filter(hecho -> {
                        return (repositorio.noExisteHecho( archivo.getId(), hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getLatitud(), hecho.getLongitud(), hecho.getFechaAcontecimiento().atStartOfDay()) == 0);}).toList();// agregar fechaAcontesimiento
                    guardarHechos(hechosModificados,archivo);
                }
            }
            ultimaFechaCarga = new Date();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private Boolean esPosteriorAUltimaCarga(String path){
        File archivo = new File(path);
        if (archivo.exists()) {
            Date fechaModificacion = new Date(archivo.lastModified());
            return fechaModificacion.after(ultimaFechaCarga);
        }
        else {
           System.out.println("Archivo con path " + path +" no encontrado");
           return false;
        }
    }

    @Transactional
    public List<Hecho> obtenerNoEnviados() {
        List<Hecho> hechosNoEnviados = repositorio.findAllByProcesadoFalse();
        hechosNoEnviados.forEach(h -> {
            h.setProcesado(true);
            repositorio.save(h);});
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
        return new HechoDTO(hecho.getTitulo(), hecho.getDescripcion(), hecho.getArchivo().getId(),hecho.getCategoria(), hecho.getFechaAcontecimiento(), Double.parseDouble(hecho.getLatitud()),  Double.parseDouble(hecho.getLongitud()));
    }
    private void guardarHechos(List<HechoCSV> hechosCSV, Archivo archivo) {
        for (HechoCSV hechoCSV : hechosCSV) {
            Hecho hecho = convertToHecho(hechoCSV, archivo);
            if (hecho.getFechaAcontecimiento().isBefore(LocalDateTime.now()) || hecho.getFechaAcontecimiento().isEqual(LocalDateTime.now())) {
                Hecho hechoGuardado = repositorio.save(hecho); // esto era un addHecho(Hecho)
            }
        }
    }
    private Hecho convertToHecho(HechoCSV hechoCSV, Archivo archivo) {
        return new Hecho(false, hechoCSV.getFechaAcontecimiento().atStartOfDay(), hechoCSV.getLongitud(), hechoCSV.getLatitud(), hechoCSV.getCategoria(), archivo, hechoCSV.getDescripcion(), hechoCSV.getTitulo());
    }
}

