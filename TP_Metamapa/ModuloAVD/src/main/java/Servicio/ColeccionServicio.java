package Servicio;

import Modelos.DTOs.ColeccionDTO;
import Modelos.DTOs.ColeccionDTOOutput;
import Modelos.DTOs.CriterioDTO;
import Modelos.DTOs.HechoDTO;
import Modelos.Entidades.*;
import Modelos.Entidades.Consenso.Consenso;
import Modelos.Conversores.ConsensoConversor;
import Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ColeccionServicio {

    @Autowired
    ColeccionRepositorio coleccionRepositorio;
    @Autowired
    HechoRepositorio hechoRepositorio;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ConsensoConversor consensoConversor;
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    @Autowired
    PaisRepositorio paisRepositorio;
    @Autowired
    ProvinciaRepositorio provinciaRepositorio;
    @Autowired
    LocalidadRepositorio localidadRepositorio;
    @Autowired
    UbicacionRepositorio ubicacionRepositorio;


    public void crearColeccion(ColeccionDTO coleccionDTO) {
        Categoria categoria =  this.crearCategoria(coleccionDTO.getCriterio().getCategoria());
        Boolean multimedia = coleccionDTO.getCriterio().getContenido_multimedia();
        LocalDate fecha_carga_desde = coleccionDTO.getCriterio().getFecha_carga_desde();
        LocalDate fecha_carga_hasta = coleccionDTO.getCriterio().getFecha_carga_hasta();
        Pais pais = this.crearPais(coleccionDTO.getCriterio().getPais());
        Provincia provincia = this.crearProvincia(coleccionDTO.getCriterio().getProvincia(), pais);
        Localidad localidad = this.crearLocalidad(coleccionDTO.getCriterio().getLocalidad(), provincia);
        Ubicacion ubicacion = this.crearUbicacion(null, null, localidad, provincia, pais);
        LocalDate fecha_acontecimiento_desde = coleccionDTO.getCriterio().getFecha_acontecimiento_desde();
        LocalDate fecha_acontecimiento_hasta = coleccionDTO.getCriterio().getFecha_acontecimiento_hasta();
        OrigenCarga origen = OrigenCarga.valueOf(coleccionDTO.getCriterio().getOrigen_carga());
        CriteriosDePertenencia criterio_pertenencia = new CriteriosDePertenencia(coleccionDTO.getTitulo(),multimedia, categoria, fecha_carga_desde, fecha_carga_hasta, ubicacion, fecha_acontecimiento_desde, fecha_acontecimiento_hasta, origen);
        Coleccion coleccion = new Coleccion(null, coleccionDTO.getTitulo(), coleccionDTO.getDescripcion(),criterio_pertenencia);
        coleccionRepositorio.save(coleccion);
        this.avisarAgregador(coleccion.getId());
      // avisarle al agregador que hay una nueva coleccion y que le agregue los hechos que correspondan


    }


    public Categoria crearCategoria(String nombre) {
        Categoria categoria = categoriaRepositorio.findByNombre(nombre);
        if(categoria == null){
            categoria = new Categoria(nombre);
            categoriaRepositorio.save(categoria);
        }
        return categoria;
    }

    public Pais crearPais(String nombre) {
        Pais pais = paisRepositorio.findByPais(nombre);
        if(pais == null){
            pais = new Pais(nombre);
            paisRepositorio.save(pais);
        }
        return pais;
    }

    public Provincia crearProvincia(String nombre, Pais pais) {
        Provincia provincia = provinciaRepositorio.findByProvinciaAndPais(nombre, pais);
        if(provincia == null){
            provincia = new Provincia(nombre, pais);
            provinciaRepositorio.save(provincia);
        }
        return provincia;
    }

    public Localidad crearLocalidad(String nombre, Provincia provincia) {
        Localidad localidad = localidadRepositorio.findByLocalidadAndProvincia(nombre, provincia);
        if(localidad == null){
            localidad = new Localidad(nombre, provincia);
            localidadRepositorio.save(localidad);
        }
        return localidad;
    }

    public Ubicacion crearUbicacion(Double latitud, Double longitud, Localidad localidad, Provincia provincia, Pais pais) {
        Ubicacion ubicacion = ubicacionRepositorio.findByLocalidadAndProvinciaAndPais(localidad, provincia, pais);
        if(ubicacion == null){
            ubicacion = new Ubicacion(localidad, provincia, pais, latitud, longitud);
            ubicacionRepositorio.save(ubicacion);
        }
        return ubicacion;
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

    public void eliminarHechoDeColeccion(Long id_coleccion, Long id_hecho) {
        Coleccion coleccion = coleccionRepositorio.findById(id_coleccion).orElseThrow();
        coleccion.getHechos().removeIf(hecho -> hecho.getId().equals(id_hecho));
        coleccionRepositorio.save(coleccion);
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
        Consenso estrategia = consensoConversor.convertToEntityAttribute(nombre);
        if (estrategia == null) {
            throw new IllegalArgumentException("Estrategia no encontrada: " + nombre);
        }
        return estrategia;
    } // TODO :VER SI CON EL COVERSOR HAY QUE HACER ESTO


    public void agregarFuente(Long id, Long fuente) {
        Coleccion  coleccion = coleccionRepositorio.findById(id).orElse(null);

        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada: " + id_coleccion);
        }
        List<Hecho> hechoFuente = hechoRepositorio.findByIdFuente(fuente);

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

