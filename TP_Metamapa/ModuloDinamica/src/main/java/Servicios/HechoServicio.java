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
        Contenido contenido = new Contenido(dto.getContenido().getTexto(), dto.getContenido().getContenido_multimedia());
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(), null, null);
        LocalDate fechaOcurrencia =  dto.getFecha();
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), null, null, null); //Decision de diseño.
        boolean anonimo = dto.getAnonimo();
        LocalDate fecha_carga = LocalDate.now();
        List<Etiqueta> etiquetas = new ArrayList<>();

        Hecho hecho = new Hecho(idHecho, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion, fecha_carga, OrigenCarga.FUENTE_DINAMICA,
                true, contribuyente, anonimo, etiquetas);
        hechoRepositorio.guardarHecho(hecho);
    }

}