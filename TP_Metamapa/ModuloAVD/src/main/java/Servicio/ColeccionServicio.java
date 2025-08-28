package Servicio;

import Modelos.DTOs.ColeccionDTO;
import Modelos.Entidades.*;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import Servicio.Conversores.ConversorCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Servicio.Consenso.*;

import java.time.LocalDate;
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
        Categoria categoria = ConversorCategoria.convert(coleccionDTO.getCriterio().getCategoria());
        Boolean multimedia = coleccionDTO.getCriterio().getContenido_multimedia();
        LocalDate fecha_carga_desde = coleccionDTO.getCriterio().getFecha_carga_desde();
        LocalDate fecha_carga_hasta = coleccionDTO.getCriterio().getFecha_carga_hasta();
        String lugar = coleccionDTO.getCriterio().getLugar();
        LocalDate fecha_acontecimiento_desde = coleccionDTO.getCriterio().getFecha_acontecimiento_desde();
        LocalDate fecha_acontecimiento_hasta = coleccionDTO.getCriterio().getFecha_acontecimiento_hasta();
        OrigenCarga origen = OrigenCarga.valueOf(coleccionDTO.getCriterio().getOrigen_carga());
        CriteriosDePertenencia criterio_pertenencia = new CriteriosDePertenencia(coleccionDTO.getTitulo(),multimedia, categoria, fecha_carga_desde, fecha_carga_hasta, lugar, fecha_acontecimiento_desde, fecha_acontecimiento_hasta, origen);
        List<Hecho> hechos = new ArrayList<>();
        Coleccion coleccion = new Coleccion(UUID.randomUUID(), coleccionDTO.getTitulo(), coleccionDTO.getDescripcion(),criterio_pertenencia,hechos);
        coleccionRepositorio.agregar(coleccion);
      // avisarle al agregador que hay una nueva coleccion y que le agregue los hechos que correspondan
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


    public void agregarFuente(UUID id, UUID fuente) {
        Coleccion  coleccion = coleccionRepositorio.obtenerPorId(id);
        List<Hecho> hechoFuente = hechoRepositorio.hechosConFuente(fuente);
        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada:");
        }
        if (hechoFuente.isEmpty()) {
            throw new IllegalArgumentException("No hay hechos de esa fuente");
        }
        for (Hecho hecho: hechoFuente) {
            coleccion.agregarHecho(hecho);
        }
    }
    public void eliminarFuente(UUID id, UUID fuente) {
        coleccionRepositorio.eliminarHechosFuente(id, fuente);
    }


}

