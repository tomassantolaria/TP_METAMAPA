package Servicio;

import Modelos.DTOs.ColeccionDTO;
import Modelos.Entidades.*;
import Modelos.Entidades.Consenso.Consenso;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Service
public class ColeccionServicio {

    private final Map<String, Consenso> consensosMap;
    ColeccionRepositorio coleccionRepositorio;
    HechoRepositorio hechoRepositorio;
    RestTemplate restTemplate;

    public ColeccionServicio(ColeccionRepositorio coleccionRepositorio, HechoRepositorio hechoRepositorio, Map<String, Consenso> consensosMap) {
        this.coleccionRepositorio = coleccionRepositorio;
        this.hechoRepositorio = hechoRepositorio;
        this.consensosMap = consensosMap;
    }


    public void crearColeccion(ColeccionDTO coleccionDTO) {
        Categoria categoria =  new Categoria(coleccionDTO.getCriterio().getCategoria());
        Boolean multimedia = coleccionDTO.getCriterio().getContenido_multimedia();
        LocalDate fecha_carga_desde = coleccionDTO.getCriterio().getFecha_carga_desde();
        LocalDate fecha_carga_hasta = coleccionDTO.getCriterio().getFecha_carga_hasta();
        Provincia provincia = new Provincia(coleccionDTO.getCriterio().getProvincia());
        Localidad localidad = new Localidad(coleccionDTO.getCriterio().getLocalidad(), provincia);
        Calle calle = new Calle (coleccionDTO.getCriterio().getCalle(), localidad);
        Ubicacion ubicacion = new Ubicacion(calle, localidad, provincia, null, null);
        LocalDate fecha_acontecimiento_desde = coleccionDTO.getCriterio().getFecha_acontecimiento_desde();
        LocalDate fecha_acontecimiento_hasta = coleccionDTO.getCriterio().getFecha_acontecimiento_hasta();
        OrigenCarga origen = OrigenCarga.valueOf(coleccionDTO.getCriterio().getOrigen_carga());
        CriteriosDePertenencia criterio_pertenencia = new CriteriosDePertenencia(coleccionDTO.getTitulo(),multimedia, categoria, fecha_carga_desde, fecha_carga_hasta, ubicacion, fecha_acontecimiento_desde, fecha_acontecimiento_hasta, origen);
        List<Hecho> hechos = new ArrayList<>();
        Coleccion coleccion = new Coleccion(null, coleccionDTO.getTitulo(), coleccionDTO.getDescripcion(),criterio_pertenencia,hechos);
        coleccionRepositorio.save(coleccion);
        this.avisarAgregador(coleccion.getId());
      // avisarle al agregador que hay una nueva coleccion y que le agregue los hechos que correspondan


    }
    private void avisarAgregador (Long coleccionId) {
        UriComponentsBuilder urlAgregador = UriComponentsBuilder.fromPath("http://coleccionCreada/" + coleccionId);
        try {
            restTemplate.exchange(
                    urlAgregador.toUriString(),
                    HttpMethod.POST,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Error al comunicarse con el servicio Agregador: " + e.getMessage());
        }


    }

    public void eliminarColeccion(Long id) {
        coleccionRepositorio.deleteById(id);
    }

    public void eliminarHecho(Long id) {
        coleccionRepositorio.deleteById(id);
        hechoRepositorio.deleteById(id);
    }

    public void modificarConsenso(Long id, String estrategia) {
        Coleccion coleccion = coleccionRepositorio.findById(id).orElse(null);
        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada:");
        }
        Consenso consenso = this.obtenerEstrategiaPorNombre(estrategia);
        coleccion.setConsenso(consenso);
    }

    private Consenso obtenerEstrategiaPorNombre(String nombre) {
        Consenso estrategia = consensosMap.get(nombre);
        if (estrategia == null) {
            throw new IllegalArgumentException("Estrategia no encontrada: " + nombre);
        }
        return estrategia;
    } // TODO :VER SI CON EL COVERSOR HAY QUE HACER ESTO


    public void agregarFuente(Long id, Long fuente) {
        Coleccion  coleccion = coleccionRepositorio.findById(id).orElse(null);

        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada:");
        }
        List<Hecho> hechoFuente = hechoRepositorio.findByFuente(fuente);

        if (hechoFuente.isEmpty()) {
            throw new IllegalArgumentException("No hay hechos de esa fuente");
        }
        for (Hecho hecho: hechoFuente) {
            coleccion.agregarHecho(hecho);
        }
        coleccionRepositorio.save(coleccion);
    }
    public void eliminarFuente(Long id, Long fuente) {
        Coleccion coleccion = coleccionRepositorio.findById(id).orElse(null);
        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada:");
        }
        coleccion.getHechos().removeIf(hecho -> hecho.getIdFuente().equals(fuente));
        coleccionRepositorio.save(coleccion);
    }
    //TODO :REVISAR QUE EL ELIMINAR FUENTE DEBERIA HACERLO PRO LA COMBINACION DE ID Y DE FUENTE


}

