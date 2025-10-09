package Servicio;


import Modelos.Entidades.*;
import Modelos.Entidades.Excepciones.ColeccionNotFoundException;
import Modelos.HechoDTO;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavegadorServicio {

  @Autowired
  HechoRepositorio hechoRepositorio;
  @Autowired
  ColeccionRepositorio coleccionRepositorio;


    public List<HechoDTO> filtrarHechos(Long idColeccion, String categoria, Boolean contenidoMultimedia, LocalDate fechaCargaDesde, LocalDate fechaCargaHasta, LocalDate fechaHechoDesde, LocalDate fechaHechoHasta, String origenCarga, String titulo, String pais, String provincia, String localidad) {
        List <Hecho> hechos;

        OrigenCarga origenCargaNuevo = crearOrigen(origenCarga);

        if(idColeccion == null){
            hechos = hechoRepositorio.filtrarHechos(categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCargaNuevo, titulo, pais, provincia, localidad);

        }else{
             this.validarColeccion(idColeccion);
             hechos = coleccionRepositorio.filtrarHechosEnColeccion(idColeccion, categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCargaNuevo, titulo, pais, provincia, localidad);
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
        String contenidoMultimedia = hecho.getContenido() != null ? hecho.getContenido().getContenido_multimedia() : null;
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
        LocalDate fechaNacimiento = null;

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
}
