package Servicios;

import Repositorios.CategoriaRepositorio;
import Repositorios.HechoRepositorio;
import Modelos.Entidades.*;
import Modelos.DTOs.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HechoServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    private HechoRepositorio hechoRepositorio;

    public void crearHecho(HechoDTO dto) {

        Categoria categoria = categoriaRepositorio.crearCategoria(dto.getCategoria());
        Contenido contenido = new Contenido(dto.getContenido(),dto.getContenido_multimedia());
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(), dto.getLatitud(), dto.getLongitud());
        LocalDate fechaOcurrencia =  dto.getFechaAcontecimiento();
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), dto.getNombre(), dto.getApellido(), dto.getFecha_nacimiento()); //Decision de diseño.
        boolean anonimo = dto.getAnonimo();

        Hecho hecho = new Hecho(null,null, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion,
                                contribuyente, anonimo, true); // VER COMO ASIGNAR UN ID A LAS DISTINTAS FUENTES!!!!!!!!
        hechoRepositorio.guardarHecho(hecho);
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
                    hecho.getUbicacion().getNombre(),
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