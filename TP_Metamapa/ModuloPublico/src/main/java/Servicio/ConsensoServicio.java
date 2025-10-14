package Servicio;

import java.time.LocalDateTime ;
import java.util.*;
import Modelos.Entidades.*;
import Modelos.Entidades.Excepciones.ColeccionNotFoundException;
import Modelos.HechoDTO;
import Repositorio.ColeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsensoServicio {

    @Autowired
    ColeccionRepositorio coleccionRepositorio;

    public List<HechoDTO> hechosConConsenso (Long id) {
        Coleccion coleccion = coleccionRepositorio.findById(id).orElse(null);
        if (coleccion == null ) {
            throw new ColeccionNotFoundException("Colección no encontrada con id: "  + id);
        }
        List<Hecho> hechos = coleccion.getHechosConsensuados();
        List<HechoDTO> hechoDTOS = new ArrayList<>();
        for (Hecho hecho : hechos) {
            hechoDTOS.add(transformarAHechoDTO(hecho));
        }
        return hechoDTOS;
    }

    public List<HechoDTO> hechosIrrestrictos(Long id) {
        Coleccion coleccion = coleccionRepositorio.findById(id).orElse(null);
        if (coleccion == null ) {
            throw new ColeccionNotFoundException("Colección no encontrada con id: "  + id);
        }
        List<Hecho> hechos = coleccion.getHechos();
        List<HechoDTO> hechoDTOS = new ArrayList<>();
        for (Hecho hecho : hechos) {
            hechoDTOS.add(transformarAHechoDTO(hecho));
        }
        return hechoDTOS;
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
}
