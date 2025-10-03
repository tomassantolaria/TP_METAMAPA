package Servicio;

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

    public HechoDTO transformarAHechoDTO (Hecho hecho){
        HechoDTO hechoDTO = new HechoDTO(hecho.getId(), hecho.getIdFuente(),hecho.getTitulo(),hecho.getDescripcion(), hecho.getContenido().getTexto(),hecho.getContenido().getContenido_multimedia(),hecho.getCategoria().getNombre(), hecho.getFecha(), hecho.getFecha_carga(), hecho.getUbicacion().getLocalidad().getLocalidad(), hecho.getUbicacion().getProvincia().getProvincia(), hecho.getUbicacion().getPais().getPais(), hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud(), hecho.getContribuyente().getUsuario(), null, null, null, null, null, hecho.getOrigen().name());
        if (hecho.isAnonimo()) {
            hechoDTO.setUsuario(null);
        }
        return hechoDTO;
    }
}