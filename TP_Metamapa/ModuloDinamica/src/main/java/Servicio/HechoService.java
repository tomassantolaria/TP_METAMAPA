package Servicio;

import Repositorio.HechoRepository;
import Modelos.Entidades.*;
import Modelos.DTOs.*;
import Repositorio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HechoService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    private HechoRepository hechoRepository;

    public void crearHecho(HechoDTO dto) {
        String idHecho = UUID.randomUUID().toString(); //https://www.baeldung.com/java-uuid
        Categoria categoria = categoriaRepository.crearCategoria(dto.getCategoria());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(dto.getContenido_multimedia());
        Contenido contenido = new Contenido(dto.getContenido(),contenido_multimedia);
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(), null, null);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate fechaOcurrencia =  LocalDate.parse(dto.getFecha(), formato);
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), null, null, null); //Decision de diseño.
        boolean anonimo = Boolean.parseBoolean(dto.getAnonimo());
        LocalDate fecha_carga = LocalDate.now();
        List<Etiqueta> etiquetas = new ArrayList<>();

        Hecho hecho = new Hecho(idHecho, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion, fecha_carga, OrigenCarga.FUENTE_DINAMICA,
                true, contribuyente, anonimo, etiquetas);
        hechoRepository.guardarHecho(hecho);
    }

}
