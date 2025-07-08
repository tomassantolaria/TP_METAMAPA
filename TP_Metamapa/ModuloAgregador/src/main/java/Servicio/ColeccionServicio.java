package Servicio;

import Modelos.DTOs.ColeccionDTO;
import Modelos.Entidades.*;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import Servicio.Consenso.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Service
public class ColeccionServicio {
    @Autowired
    private Map<String, Consenso> consensosMap;
    private final ColeccionRepositorio coleccionRepositorio;
    private final HechoRepositorio hechoRepositorio;

    public ColeccionServicio(ColeccionRepositorio coleccionRepositorio, HechoRepositorio hechoRepositorio) {
        this.coleccionRepositorio = coleccionRepositorio;
        this.hechoRepositorio = hechoRepositorio;
    }


    public void crearColeccion(ColeccionDTO coleccionDTO) {
        Categoria categoria = ConversorCategoria.convert(coleccionDTO.getCriterio_pertenencia().getCategoria());
        Contenido contenido = ConversorContenido.convert(coleccionDTO.getCriterio_pertenencia().getContenido_texto(), coleccionDTO.getCriterio_pertenencia().getContenido_multimedia());
        LocalDate fecha = coleccionDTO.getCriterio_pertenencia().getFecha();
        Ubicacion ubicacion = ConversorUbicacion.convert(coleccionDTO.getCriterio_pertenencia().getLugar(),coleccionDTO.getCriterio_pertenencia().getLatitud(),coleccionDTO.getCriterio_pertenencia().getLongitud());
        LocalDate fechaCarga = coleccionDTO.getCriterio_pertenencia().getFecha_carga();
        OrigenCarga origen = OrigenCarga.valueOf(coleccionDTO.getCriterio_pertenencia().getOrigen_carga());
        CriteriosDePertenencia criterio = new CriteriosDePertenencia(coleccionDTO.getTitulo(),coleccionDTO.getDescripcion(), contenido, categoria, fecha,ubicacion,fechaCarga, origen);
        List<Hecho> hechos = new ArrayList<>();
        Coleccion coleccion = new Coleccion(UUID.randomUUID(), coleccionDTO.getTitulo(), coleccionDTO.getDescripcion(),criterio,hechos);
        coleccionRepositorio.agregar(coleccion);
    }

    public void eliminarColeccion(UUID id) {
        coleccionRepositorio.eliminarColeccion(id);
    }

    public void eliminarHecho(UUID id) {
        coleccionRepositorio.eliminarHecho(id);
        hechoRepositorio.eliminarHecho(id);
    }

    public void modificarConsenso(UUID id, String estrategia) {
        Coleccion coleccion = coleccionRepositorio.obtenerPorId(id);
        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada:");
        }
        coleccionRepositorio.modificarConsenso(id,obtenerEstrategiaPorNombre(estrategia));
    }

    private Consenso obtenerEstrategiaPorNombre(String nombre) {
        Consenso estrategia = consensosMap.get(nombre);
        if (estrategia == null) {
            throw new IllegalArgumentException("Estrategia no encontrada: " + nombre);
        }
        return estrategia;
    }

    public void actalizarHechosConsensuados() {
       for (Coleccion coleccion : coleccionRepositorio.getTodas()) {
            coleccionRepositorio.actualizarColeccionConsesuado(actualizarHechosConsensuados(coleccion), coleccion.getId() );
       }
    }

    public List<Hecho> actualizarHechosConsensuados(Coleccion coleccion) {
        if (coleccion.getConsenso() != null ) {
           List<Hecho> hechosconsensuados = coleccion.getHechos().stream().filter(hecho -> coleccion.getConsenso().tieneConsenso(hecho)).toList();
            return hechosconsensuados;
        }

            List<Hecho> hechosconsensuados = coleccion.getHechos();
            return hechosconsensuados;

    }

    public void agregarFuente(UUID id, String fuente) {
        
    }
    public void eliminarFuente(UUID id, String fuente) {

    }


}

