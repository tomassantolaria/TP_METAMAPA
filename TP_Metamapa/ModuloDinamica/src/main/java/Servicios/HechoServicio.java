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
        UUID idHecho = UUID.randomUUID(); //https://www.baeldung.com/java-uuid
        Categoria categoria = categoriaRepositorio.crearCategoria(dto.getCategoria());
        Contenido contenido = new Contenido(dto.getContenido(),dto.getContenido_multimedia());
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(), dto.getLatitud(), dto.getLongitud());
        LocalDate fechaOcurrencia =  dto.getFechaAcontecimiento();
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), null, null, null); //Decision de diseño.
        boolean anonimo = dto.getAnonimo();

        Hecho hecho = new Hecho(idHecho, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion,
                                contribuyente, anonimo, true);
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
                    null,
                    null,
                    null,
                    hecho.getAnonimo(),
                    hecho.getVisible(),
                    null
            );
            hechosDTO.add(dto);
        }
        return hechosDTO;
    }

}