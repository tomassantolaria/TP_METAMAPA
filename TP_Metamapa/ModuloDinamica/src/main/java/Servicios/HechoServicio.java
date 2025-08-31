package Servicios;

import Repositorios.CategoriaRepositorio;
import Repositorios.HechoRepositorio;
import Modelos.Entidades.*;
import Modelos.DTOs.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HechoServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    private HechoRepositorio hechoRepositorio;

    public void crearHecho(HechoDTO dto) {

        Categoria categoria = categoriaRepositorio.crearCategoria(dto.getCategoria());
        Contenido contenido = new Contenido(dto.getContenido(),dto.getContenido_multimedia());
        Provincia provincia = new Provincia(dto.getNombre_provincia());
        Localidad localidad = new Localidad(dto.getNombre_localidad(), provincia);
        Calle calle = new Calle(dto.getNombre_calle(), localidad);
        Ubicacion ubicacion = new Ubicacion(calle, localidad, provincia, dto.getLatitud(), dto.getLongitud());
        LocalDate fechaOcurrencia =  dto.getFechaAcontecimiento();
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), dto.getNombre(), dto.getApellido(), dto.getFecha_nacimiento()); //Decision de diseño.
        boolean anonimo = dto.getAnonimo();

        Hecho hecho = new Hecho(null,null, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion,
                                contribuyente, anonimo, true);
        // AGREGUE VERIFICACION DE FECHA !!!!!
        if (hecho.getFecha().isBefore(LocalDate.now()) || hecho.getFecha().isEqual(LocalDate.now())) {
            hechoRepositorio.guardarHecho(hecho);
        }
    }

    public List<HechoDTO> obtenerHechos() {
        List<Hecho> hechos = hechoRepositorio.obtenerTodosLosHechos();
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
                    hecho.getUbicacion().getCalle().getNombre_calle(),
                    hecho.getUbicacion().getLocalidad().getNombre_localidad(),
                    hecho.getUbicacion().getProvincia().getNombre_provincia(),
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