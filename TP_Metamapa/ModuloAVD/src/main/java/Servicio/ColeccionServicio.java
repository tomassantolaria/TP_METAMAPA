package Servicio;

import Modelos.DTOs.ColeccionDTO;
import Modelos.DTOs.ColeccionDTOOutput;
import Modelos.DTOs.CriterioDTO;
import Modelos.DTOs.HechoDTO;
import Modelos.Entidades.*;
import Modelos.Entidades.Consenso.Consenso;
import Modelos.Conversores.ConsensoConversor;
import Modelos.Entidades.Consenso.ConsensoAbsoluta;
import Modelos.Entidades.Consenso.ConsensoMayoriaSimple;
import Modelos.Entidades.Consenso.ConsensoMultiplesMenciones;
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
import java.util.Optional;


@Service
public class ColeccionServicio {

    @Autowired
    ColeccionRepositorio coleccionRepositorio;
    @Autowired
    HechoRepositorio hechoRepositorio;
    @Autowired
    RestTemplate restTemplate;
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
    @Autowired
    CriterioPertenenciaRepositorio criterioPertenenciaRepositorio;


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
        OrigenCarga origen = this.crearOrigen(coleccionDTO.getCriterio().getOrigen_carga());
        CriteriosDePertenencia criterio_pertenencia = new CriteriosDePertenencia(coleccionDTO.getCriterio().getTitulo(),multimedia, categoria, fecha_carga_desde, fecha_carga_hasta, ubicacion, fecha_acontecimiento_desde, fecha_acontecimiento_hasta, origen);
        criterioPertenenciaRepositorio.save(criterio_pertenencia);
        Coleccion coleccion = new Coleccion(coleccionDTO.getTitulo(), coleccionDTO.getDescripcion(),criterio_pertenencia);
        coleccionRepositorio.save(coleccion);
        System.out.printf("El id es: %d" ,coleccion.getId());
       // this.avisarAgregador(coleccion.getId());
      // avisarle al agregador que hay una nueva coleccion y que le agregue los hechos que correspondan


    }


    public Categoria crearCategoria(String nombre) {
        if (nombre == null){
            return null;
        }
        Categoria categoria = categoriaRepositorio.findByNombre(nombre);
        if(categoria == null){
            categoria = new Categoria(nombre);
            categoriaRepositorio.save(categoria);
        }
        return categoria;
    }

    public Pais crearPais(String nombre) {
        if (nombre == null) {
            return null;
        }
        Pais pais = paisRepositorio.findByPais(nombre);
        if(pais == null){
            pais = new Pais(nombre);
            paisRepositorio.save(pais);
        }
        return pais;
    }

    public Provincia crearProvincia(String nombre, Pais pais) {
        if (nombre == null) {
            return null;
        }
        Provincia provincia = provinciaRepositorio.findByProvinciaAndPais(nombre, pais);
        if(provincia == null){
            provincia = new Provincia(nombre, pais);
            provinciaRepositorio.save(provincia);
        }
        return provincia;
    }

    public Localidad crearLocalidad(String nombre, Provincia provincia) {
        if (nombre == null) {
            return null;
        }
        Localidad localidad = localidadRepositorio.findByLocalidadAndProvincia(nombre, provincia);
        if(localidad == null){
            localidad = new Localidad(nombre, provincia);
            localidadRepositorio.save(localidad);
        }
        return localidad;
    }

    public Ubicacion crearUbicacion(Double latitud, Double longitud, Localidad localidad, Provincia provincia, Pais pais) {
        if (pais == null) {
            return null;
        }
        Ubicacion ubicacion = ubicacionRepositorio.findByLocalidadAndProvinciaAndPais(localidad, provincia, pais);
        if(ubicacion == null){
            ubicacion = new Ubicacion(localidad, provincia, pais, latitud, longitud);
            ubicacionRepositorio.save(ubicacion);
        }
        return ubicacion;
    }

    public OrigenCarga crearOrigen(String origen){
        if (origen == null) {
            return null;
        }
        return OrigenCarga.valueOf(origen);
    }

    private void avisarAgregador (Long coleccionId) {
        UriComponentsBuilder urlAgregador = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/colecciones/" + coleccionId);
        restTemplate.exchange(
                urlAgregador.toUriString(),
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<>() {
                }
                );
    }

    public void eliminarColeccion(Long id) {
        coleccionRepositorio.deleteById(id);
    }

    public void eliminarHecho(Long id) {
        List<Coleccion> colecciones = coleccionRepositorio.findAll();
        for (Coleccion coleccion : colecciones) {
            this.eliminarHechoDeColeccion(coleccion.getId(), id);
        }
        Hecho hecho = hechoRepositorio.findById(id).orElseThrow();
        hecho.eliminarse();
        hechoRepositorio.save(hecho);
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
        coleccionRepositorio.save(coleccion);
    }
    private Consenso transformarAConsenso(String nombre) {
            if (nombre == null || nombre.equals("SIN_CONSENSO")) {
            return null;
        }
        switch (nombre) {
            case "ABSOLUTA": return new ConsensoAbsoluta();
            case "MULTIPLES_MENCIONES": return new ConsensoMultiplesMenciones();
            case "MAYORIA_SIMPLE": return new ConsensoMayoriaSimple();
            default:
                throw new IllegalArgumentException("Tipo de CONSENSO desconocido: " + nombre);
        }
    }
    private Consenso obtenerEstrategiaPorNombre(String nombre) {
        Consenso estrategia = transformarAConsenso(nombre);

        return estrategia;
    }


    public void agregarFuente(Long id_coleccion, Long id_fuente, String origen) {
        Coleccion  coleccion = coleccionRepositorio.findById(id_coleccion).orElse(null);

        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada: " + id_coleccion);
        }
        List<Hecho> hechoFuente = hechoRepositorio.findByIdFuenteAndOrigen(id_fuente, OrigenCarga.valueOf(origen.toUpperCase()));
        if (hechoFuente.isEmpty()) {
            throw new IllegalArgumentException("No hay hechos de esa fuente");
        }
        for (Hecho hecho: hechoFuente) {
            coleccion.agregarHecho(hecho);
        }
        coleccionRepositorio.save(coleccion);
    }
    public void eliminarFuente(Long id, Long fuente, String origen) {
        Coleccion coleccion = coleccionRepositorio.findById(id).orElse(null);
        if (coleccion == null) {
            throw new IllegalArgumentException("Coleccion no encontrada:");
        }
        coleccion.getHechos().removeIf(hecho -> hecho.getIdFuente().equals(fuente) && hecho.getOrigen().equals(OrigenCarga.valueOf(origen)));
        coleccionRepositorio.save(coleccion);
    }

    public ColeccionDTOOutput obtenerColeccion(Long id) {
        Coleccion coleccion = coleccionRepositorio.findById(id).orElseThrow();
        return this.transformarColeccionADTO(coleccion);
    }

    public CriterioDTO transformarCriterioADTO(CriteriosDePertenencia criteriosDePertenencia) {
        return new CriterioDTO(
                criteriosDePertenencia.getTitulo(),
                criteriosDePertenencia.getMultimedia(),
                Optional.ofNullable(criteriosDePertenencia.getCategoria())
                        .map(c->c.getNombre())
                        .orElse(null),
                criteriosDePertenencia.getFecha_carga_desde(),
                criteriosDePertenencia.getFecha_carga_hasta(),
                Optional.ofNullable(criteriosDePertenencia.getUbicacion())
                    .map(u -> u.getLocalidad())
                    .map(l -> l.getLocalidad())
                    .orElse(null),
                Optional.ofNullable(criteriosDePertenencia.getUbicacion())
                        .map(u -> u.getProvincia())
                        .map(p -> p.getProvincia())
                        .orElse(null),
                Optional.ofNullable(criteriosDePertenencia.getUbicacion())
                        .map(u -> u.getPais())
                        .map(p -> p.getPais())
                        .orElse(null),
                criteriosDePertenencia.getFecha_acontecimiento_desde(),
                criteriosDePertenencia.getFecha_acontecimiento_hasta(),
                Optional.ofNullable(criteriosDePertenencia.getOrigen())
                        .map(o->o.name())
                        .orElse(null)
        );
    }

    public HechoDTO transformarHechoDTO(Hecho hecho) {

        HechoDTO dto = new HechoDTO(
                hecho.getTitulo(),
                hecho.getDescripcion(),
                hecho.getContenido().getTexto(),
                hecho.getContenido().getContenido_multimedia(),
                hecho.getCategoria().getNombre(),
                hecho.getFecha(),
                hecho.getUbicacion().getPais().getPais(),
                hecho.getUbicacion().getProvincia().getProvincia(),
                hecho.getUbicacion().getLocalidad().getLocalidad(),
                null,
                null,
                null,
                null,
                hecho.getOrigen().name());

        if(!hecho.isAnonimo()){
            dto.setUsuario(hecho.getContribuyente().getUsuario());
            dto.setApellido(hecho.getContribuyente().getApellido());
            dto.setNombre(hecho.getContribuyente().getNombre());
            dto.setFecha_nacimiento(hecho.getContribuyente().getFecha_nacimiento());
        }
        return dto;
    }

    public ColeccionDTOOutput transformarColeccionADTO(Coleccion coleccion) {
        CriterioDTO criterio = this.transformarCriterioADTO(coleccion.getCriterio_pertenencia());
        List<HechoDTO> hechosDTO = new ArrayList<>();
        for (Hecho hecho: coleccion.getHechos() ) {
            HechoDTO hechoDTO = this.transformarHechoDTO(hecho);
            hechosDTO.add(hechoDTO);
        }

        String consenso = Optional.ofNullable(coleccion.getConsenso())
                .map(c -> c.toString())
                .orElse(null);

        List<HechoDTO> hechosConsensuadosDTO = new ArrayList<>();
        for (Hecho hecho: coleccion.getHechosConsensuados() ) {
            HechoDTO hechoDTO = this.transformarHechoDTO(hecho);
            hechosConsensuadosDTO.add(hechoDTO);
        }
        return new ColeccionDTOOutput(coleccion.getTitulo(), coleccion.getDescripcion(), criterio, hechosDTO, consenso,hechosConsensuadosDTO );
    }
    //TODO :REVISAR QUE EL ELIMINAR FUENTE DEBERIA HACERLO PRO LA COMBINACION DE ID Y DE FUENTE


}

