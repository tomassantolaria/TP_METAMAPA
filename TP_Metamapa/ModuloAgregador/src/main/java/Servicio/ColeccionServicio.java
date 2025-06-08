package Servicio;

import Controlador.Modelos.DTOs.ColeccionDTO;
import Controlador.Modelos.Entidades.*;
import Controlador.Modelos.Entidades.Coleccion;
import Repositorio.ColeccionRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ColeccionServicio {

    private final ColeccionRepositorio coleccionRepositorio;

    public ColeccionServicio(ColeccionRepositorio coleccionRepositorio) {
        this.coleccionRepositorio = coleccionRepositorio;
    }

    public Coleccion obtenerOCriarExcepcion(String id) {
        try {
            return coleccionRepositorio.obtenerPorId(id);
        } catch (Exception e) {
            throw new RuntimeException("Colección no encontrada");
        }
    }

    public void crearColeccion(ColeccionDTO coleccionDTO) {
        String id = UUID.randomUUID().toString();
        Categoria categoria = new Categoria(coleccionDTO.getCriterio_pertenencia().getCategoria());
        ContenidoMultimedia contenidoMultimedia = new ContenidoMultimedia(coleccionDTO.getCriterio_pertenencia().getContenido_multimedia());
        Contenido contenido = new Contenido(coleccionDTO.getCriterio_pertenencia().contenido_texto, contenidoMultimedia);
        String fechaString = coleccionDTO.getCriterio_pertenencia().getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        Ubicacion ubicacion = new Ubicacion(coleccionDTO.getCriterio_pertenencia().getLugar(),coleccionDTO.getCriterio_pertenencia().getLatitud(),coleccionDTO.getCriterio_pertenencia().getLongitud());
        String fechaCargaString = coleccionDTO.getCriterio_pertenencia().getFecha_carga();
        DateTimeFormatter formatterCarga = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaCarga = LocalDate.parse(fechaString, formatter);
        OrigenCarga origen = OrigenCarga.valueOf(coleccionDTO.getCriterio_pertenencia().getOrigen_carga());
        CriterioDePertenencia criterio = new CriterioDePertenencia(coleccionDTO.getTitulo(),coleccionDTO.getDescripcion(), contenido, categoria, fecha,ubicacion,fechaCarga, origen,coleccionDTO.getIdFuente());
        List<Hecho> hechos = new ArrayList<>();
        Coleccion coleccion = new Coleccion(id, coleccionDTO.getTitulo(), coleccionDTO.getDescripcion(),criterio,hechos);
        coleccionRepositorio.agregar(coleccion);
    }
}

