package Servicios;

import Modelos.HechoDTO;
import Modelos.HechoDTOInput;
import Repositorios.HechoRepositorio;
import Modelos.Entidades.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HechoServicio {

    @Autowired
    HechoRepositorio hechoRepositorio;

    public void crearHecho(HechoDTOInput dto) {
        Categoria categoria = new Categoria(dto.getCategoria());
        Contenido contenido = new Contenido(dto.getContenido(),dto.getContenido_multimedia());
        Pais pais = new Pais(dto.getPais());
        Provincia provincia = new Provincia(dto.getProvincia(), pais);
        Localidad localidad = new Localidad(dto.getLocalidad(), provincia);
        Ubicacion ubicacion = new Ubicacion(localidad, provincia, pais, dto.getLatitud(), dto.getLongitud());
        LocalDate fechaOcurrencia =  dto.getFechaAcontecimiento();
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), dto.getNombre(), dto.getApellido(), dto.getFecha_nacimiento()); //Decision de diseño.
        boolean anonimo = dto.getAnonimo();

        Hecho hecho = new Hecho(null,null, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion,
                                contribuyente, anonimo, true);
        hechoRepositorio.save(hecho);
    }

    public List<HechoDTO> obtenerHechos() {
        List<Hecho> hechos = hechoRepositorio.findAll();
        return transformarADTOLista(hechos);
    }

    private List<HechoDTO> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTO> hechosDTO = new ArrayList<>();
        for (Hecho hecho : hechos) {
            HechoDTO dto = new HechoDTO(
                    null,
                    hecho.getIdfuente(),
                    hecho.getTitulo(),
                    hecho.getDescripcion(),
                    hecho.getContenido().getTexto(),
                    hecho.getContenido().getContenido_multimedia(),
                    hecho.getCategoria().getNombre(),
                    hecho.getFecha(),
                    null,
                    hecho.getUbicacion().getLocalidad().getNombre_localidad(),
                    hecho.getUbicacion().getProvincia().getNombre_provincia(),
                    hecho.getUbicacion().getPais().getNombre_pais(),
                    hecho.getUbicacion().getLatitud(),
                    hecho.getUbicacion().getLongitud(),
                    hecho.getContribuyente().getUsuario(),
                    hecho.getContribuyente().getNombre(),
                    hecho.getContribuyente().getApellido(),
                    hecho.getContribuyente().getFecha_nacimiento(),
                    hecho.getAnonimo(),
                    hecho.getVisible(),
                    null
            );
            hechosDTO.add(dto);
        }
        return hechosDTO;
    }

}