package Servicio;

import Modelos.DTOs.ColeccionDTO;
import Modelos.Entidades.*;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ColeccionServicio {

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

    public void eliminarHecho(String id) {
        coleccionRepositorio.eliminarHecho(id);
        hechoRepositorio.eliminarHecho(id);
    }
}

