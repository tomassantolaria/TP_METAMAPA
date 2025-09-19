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

            System.out.println("PATHS ENCONTRADOS PARA CARGAR" + paths);

            for (String path : paths) {

                Archivo archivo = archivoRepository.findByPath(path)
                        .orElseGet(() -> {
                            return null;
                        });

                if(archivo != null &&  !esPosteriorAUltimaCarga(path)) {
                    System.out.println("----------------------ARCHIVO YA REVISADO-----------------------------");
                    continue;
                }
                hechosCSV = importador.getHechoFromFile(path);
                if(archivo == null)
                {
                    System.out.println("ARCHIVO NO CREADO SE GUARDA");
                    Archivo nuevoArchivo = new Archivo();
                    nuevoArchivo.setPath(path);
                    archivoRepository.save(nuevoArchivo);
                    guardarHechos(hechosCSV,nuevoArchivo);
                }
                else {
                    System.out.println("ARCHIVO EXISTE");
                    List<HechoCSV> hechosModificados = hechosCSV.stream().filter(hecho -> (repositorio.noExisteHecho( archivo.getId(), hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getLatitud(), hecho.getLongitud(), hecho.getFechaAcontecimiento()) == 0)).toList();
                    System.out.println("HECHOS MODFICADOS --------------------------------");
                    System.out.println(hechosModificados);
                    guardarHechos(hechosModificados,archivo);
                }
            }
            ultimaFechaCarga = new Date();

        } catch (Exception e) {
            e.printStackTrace();
        }

    } // guarda a los hechos de los archivos en el repositorio


    private Boolean esPosteriorAUltimaCarga(String path){
        File archivo = new File(path);
        System.out.println("EVALUAR ARCHIVO FECHA" + archivo.getAbsolutePath());
        if (archivo.exists()) {
            System.out.println("ULT FECHA MODIF" + ultimaFechaCarga);
            Date fechaModificacion = new Date(archivo.lastModified());
            System.out.println("FECHA MODIF ARCHIVO" + fechaModificacion);
            System.out.println(fechaModificacion.after(ultimaFechaCarga));
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
        return new HechoDTO(hecho.getTitulo(), hecho.getDescripcion(), hecho.getArchivo().getId(),hecho.getCategoria(), hecho.getFechaAcontecimiento(), hecho.getLatitud(),  hecho.getLongitud());
    }
    private void guardarHechos(List<HechoCSV> hechosCSV, Archivo archivo) {
        System.out.println("ANTES DE GUARDAR HECHO");
        for (HechoCSV hechoCSV : hechosCSV) {

            Hecho hecho = convertToHecho(hechoCSV, archivo);
            // AGREGUE VERIFICACION DE FECHA !!!!!

            if (hecho.getFechaAcontecimiento().isBefore(LocalDate.now()) || hecho.getFechaAcontecimiento().isEqual(LocalDate.now())) {
                Hecho hechoGuardado = repositorio.save(hecho); // esto era un addHecho(Hecho)

            }
        }
    }
    private Hecho convertToHecho(HechoCSV hechoCSV, Archivo archivo) {
        return new Hecho(hechoCSV.getTitulo(), hechoCSV.getDescripcion(), archivo, hechoCSV.getCategoria(), hechoCSV.getFechaAcontecimiento(), hechoCSV.getLatitud(), hechoCSV.getLongitud(), false);
    }
}

