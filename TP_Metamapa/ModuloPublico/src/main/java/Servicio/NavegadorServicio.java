package Servicio;


import Modelos.ColeccionDTO;
import Modelos.CriterioDTO;
import Modelos.Entidades.*;
import Modelos.Entidades.Excepciones.ColeccionNotFoundException;
import Modelos.Entidades.Excepciones.HechosNoEncontradosException;
import Modelos.HechoDTO;
import Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime ;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NavegadorServicio {

  @Autowired
  HechoRepositorio hechoRepositorio;
  @Autowired
  ColeccionRepositorio coleccionRepositorio;
  @Autowired
  PaisRepositorio paisRepositorio;
  @Autowired
  ProvinciaRepositorio provinciaRepositorio;
  @Autowired
  LocalidadRepositorio localidadRepositorio;
  @Autowired
  CategoriaRepositorio categoriaRepositorio;

    public List<HechoDTO> filtrarHechos(Long idColeccion, String categoria, Boolean contenidoMultimedia, LocalDateTime  fechaCargaDesde, LocalDateTime  fechaCargaHasta, LocalDateTime fechaHechoDesde, LocalDateTime fechaHechoHasta, String origenCarga, String titulo, String pais, String provincia, String localidad, Boolean navegacionCurada){
        List <Hecho> hechos;

        OrigenCarga origenCargaNuevo = crearOrigen(origenCarga);

        if(idColeccion == null){
            hechos = hechoRepositorio.filtrarHechos(categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCargaNuevo, titulo, pais, provincia, localidad);

        }else{
             this.validarColeccion(idColeccion);
             if(navegacionCurada){

                 hechos = coleccionRepositorio.filtrarHechosCuradosEnColeccion(idColeccion, categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCargaNuevo, titulo, pais, provincia, localidad);

             }else{

                 hechos = coleccionRepositorio.filtrarHechosEnColeccion(idColeccion, categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCargaNuevo, titulo, pais, provincia, localidad);

             }
        }

        return transformarADTOLista(hechos);
    }

    public List<HechoDTO> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTO> hechosDTO;
        hechosDTO = hechos.stream()
                .map(this::transformarAHechoDTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTO transformarAHechoDTO(Hecho hecho) {
        if (hecho == null) return null;

        // Protecciones contra null en objetos relacionados
        String texto = hecho.getContenido() != null ? hecho.getContenido().getTexto() : null;
        String contenidoMultimedia = hecho.getContenido() != null ? hecho.getContenido().getContenidoMultimedia() : null;
        String categoria = hecho.getCategoria() != null ? hecho.getCategoria().getNombre() : null;

        String localidad = null;
        String provincia = null;
        String pais = null;
        Double latitud = null;
        Double longitud = null;
        if (hecho.getUbicacion() != null) {
            localidad = hecho.getUbicacion().getLocalidad() != null ? hecho.getUbicacion().getLocalidad().getLocalidad() : null;
            provincia = hecho.getUbicacion().getProvincia() != null ? hecho.getUbicacion().getProvincia().getProvincia() : null;
            pais = hecho.getUbicacion().getPais() != null ? hecho.getUbicacion().getPais().getPais() : null;
            latitud = hecho.getUbicacion().getLatitud();
            longitud = hecho.getUbicacion().getLongitud();
        }

        String usuario = null;
        String nombre = null;
        String apellido = null;
        LocalDateTime fechaNacimiento = null;

        if (hecho.getContribuyente() != null) {
            usuario = hecho.getContribuyente().getUsuario();
            nombre = hecho.getContribuyente().getNombre();
            apellido = hecho.getContribuyente().getApellido();
            fechaNacimiento = hecho.getContribuyente().getFecha_nacimiento();
        }

        String origen = hecho.getOrigen() != null ? hecho.getOrigen().name() : null;

        HechoDTO hechoDTO = new HechoDTO(
                hecho.getId(),
                hecho.getIdFuente(),
                hecho.getTitulo(),
                hecho.getDescripcion(),
                texto,
                contenidoMultimedia,
                categoria,
                hecho.getFecha(),
                hecho.getFecha_carga(),
                localidad,
                provincia,
                pais,
                latitud,
                longitud,
                usuario,
                nombre,
                apellido,
                fechaNacimiento,
                hecho.getAnonimo(),
                hecho.getVisible(),
                origen
        );

        // Si el hecho es anónimo, ocultar usuario
        if (hecho.getAnonimo() != null && hecho.getAnonimo()) {
            hechoDTO.setUsuario(null);
        }

        return hechoDTO;
    }

    public OrigenCarga crearOrigen(String origen){
        if (origen == null) {
            return null;
        }
        return OrigenCarga.valueOf(origen);
    }

    public void validarColeccion(Long id){
        Coleccion coleccion = coleccionRepositorio.findById(id).orElse(null);
        if (coleccion == null ) {
            throw new ColeccionNotFoundException("Colección no encontrada con id: "  + id);
        }
    }

    public List<HechoDTO> buscarPorTextoLibre(String texto){

        //es para recibir todo el texto y separarlo en una lista de palabras para ir recorriendola 
        String[] palabras = texto.toLowerCase().split("\\s+");

        List<Hecho> hechos = hechoRepositorio.buscarTodosVisibles();

        List<Hecho> filtrados = hechos.stream().filter(hecho -> {
            String textoTotal = (
                (hecho.getTitulo() != null ? hecho.getTitulo().toLowerCase() + " " : "") +
                (hecho.getDescripcion() != null ? hecho.getDescripcion().toLowerCase() + " " : "") +
                (hecho.getCategoria() != null ? hecho.getCategoria().getNombre().toLowerCase() + " " : "") +
                (hecho.getContenido() != null && hecho.getContenido().getTexto() != null ? hecho.getContenido().getTexto().toLowerCase() + " " : "") +
                (hecho.getUbicacion() != null ? (
                        (hecho.getUbicacion().getLocalidad() != null ? hecho.getUbicacion().getLocalidad().getLocalidad().toLowerCase() + " " : "") +
                        (hecho.getUbicacion().getProvincia() != null ? hecho.getUbicacion().getProvincia().getProvincia().toLowerCase() + " " : "") +
                        (hecho.getUbicacion().getPais() != null ? hecho.getUbicacion().getPais().getPais().toLowerCase() + " " : "")
                ): "")
        );

        for (String palabra : palabras) {
            if (!textoTotal.contains(palabra)) {
                return false;
            }
        }
        return true;
        }).toList();

        if (filtrados.isEmpty()) {
            throw new HechosNoEncontradosException("No se encontraron hechos que coincidan con el texto: " + texto);
        }

    return transformarADTOLista(filtrados);
}
    public List<String> obtenerPaises(){
        List<Pais> paises = paisRepositorio.findAll();
        return paises
                .stream()
                .map(Pais::getPais)
                .collect(Collectors.toList());

    }

    public List<String> obtenerProvincias(){
        List<Provincia> provincias = provinciaRepositorio.findAll();
        return provincias
                .stream()
                .map(Provincia::getProvincia)
                .collect(Collectors.toList());

    }

    public List<String> obtenerLocalidades(){
        List<Localidad> localidades = localidadRepositorio.findAll();
        return localidades
                .stream()
                .map(Localidad::getLocalidad)
                .collect(Collectors.toList());
    }

    public List<String> obtenerCategorias(){
        List<Categoria> categorias = categoriaRepositorio.findAll();
        return categorias
                .stream()
                .map(Categoria::getNombre)
                .collect(Collectors.toList());
    }

    public List<ColeccionDTO> obtenerColecciones(){
        List<Coleccion> colecciones = coleccionRepositorio.findAll();
        return this.transformarColeccionesADTOLista(colecciones);
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
                hecho.getId(),
                hecho.getIdFuente(),
                hecho.getTitulo(),
                hecho.getDescripcion(),
                Optional.ofNullable(hecho.getContenido().getTexto()).orElse(null),
                Optional.ofNullable(hecho.getContenido().getContenidoMultimedia()).orElse(null),
                hecho.getCategoria().getNombre(),
                hecho.getFecha(),
                hecho.getFecha_carga(),
                hecho.getUbicacion().getLocalidad().getLocalidad(),
                hecho.getUbicacion().getProvincia().getProvincia(),
                hecho.getUbicacion().getPais().getPais(),
                hecho.getUbicacion().getLatitud(),
                hecho.getUbicacion().getLongitud(),
                null,
                null,
                null,
                null,
                null,
                hecho.getVisible(),
                hecho.getOrigen().name());
        if (hecho.getContribuyente() != null) {
            if(Boolean.FALSE.equals(hecho.getAnonimo())){
                dto.setUsuario(hecho.getContribuyente().getUsuario());
                dto.setApellido(hecho.getContribuyente().getApellido());
                dto.setNombre(hecho.getContribuyente().getNombre());
                dto.setFecha_nacimiento(hecho.getContribuyente().getFecha_nacimiento());
            }
        }

        return dto;
    }

    public List<ColeccionDTO> transformarColeccionesADTOLista(List<Coleccion> colecciones) {
        List<ColeccionDTO> coleccionesDTO = new ArrayList<>();
        for (Coleccion coleccion: colecciones ) {
            ColeccionDTO coleccionDTO = this.transformarColeccionADTO(coleccion);
            coleccionesDTO.add(coleccionDTO);
        }
        return coleccionesDTO;
    }

    public ColeccionDTO transformarColeccionADTO(Coleccion coleccion) {
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
        return new ColeccionDTO(coleccion.getId(), coleccion.getTitulo(), coleccion.getDescripcion(),  hechosDTO, criterio, consenso,hechosConsensuadosDTO );
    }

    public HechoDTO obtenerHechoPorId(Long id) {
        Hecho hecho = hechoRepositorio.findById(id).orElse(null);
        if (hecho == null) {
            throw new HechosNoEncontradosException("Hecho no encontrado con id: " + id);
        }
        return transformarAHechoDTO(hecho);
    }
}
