package Servicio;
import java.util.*;
import Modelos.DTOs.*;
import Modelos.Entidades.*;
import Repositorio.ColeccionRepositorio;
import Repositorio.ContribuyenteRepositorio;
public class ConsensoServicio {
    private final ColeccionRepositorio coleccionRepositorio = new ColeccionRepositorio();


    public List<HechoDTO> hechosConConsenso (UUID id) {
        List<Hecho> hechos = coleccionRepositorio.obtenerPorId(id).getHechosConsensuados();
        List<HechoDTO> hechoDTOS = new ArrayList<>();
        for (Hecho hecho : hechos) {
            hechoDTOS.add(transformarAHechoDTO(hecho));
        }
        return hechoDTOS;
    }

    public List<HechoDTO> hechosIrrestrictos(UUID id) {
        List<Hecho> hechos = coleccionRepositorio.obtenerPorId(id).getHechos();
        List<HechoDTO> hechoDTOS = new ArrayList<>();
        for (Hecho hecho : hechos) {
            hechoDTOS.add(transformarAHechoDTO(hecho));
        }
        return hechoDTOS;
    }

    public HechoDTO transformarAHechoDTO (Hecho hecho){
        HechoDTO hechoDTO = new HechoDTO(hecho.getTitulo(),hecho.getDescripcion(), hecho.getContenido().getTexto(),hecho.getContenido().getContenido_multimedia(),hecho.getCategoria().getNombre(), hecho.getFecha(), hecho.getFecha_carga(), hecho.getUbicacion().getNombre(), hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud(), hecho.getUsuario(), null, null, null, null, null, hecho.getOrigen_carga().name());
        if (hecho.isAnonimo()) {
            hechoDTO.setUsuario(null);
        }
        return hechoDTO;
    }
}
