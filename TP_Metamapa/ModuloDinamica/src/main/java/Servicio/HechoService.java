package Servicio;

import Repositorio.HechoRepository;
import Modelos.Entidades.*;
import Modelos.DTOs.*;
import Repositorio.*;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HechoService {

    private final CategoriaRepository categoriaRepository;
    private final HechoRepository hechoRepository;

    public HechoService(CategoriaRepository categoriaRepository, HechoRepository hechoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.hechoRepository = hechoRepository;
    }

    public void crearHecho(HechoDTO dto) {
        String idHecho = UUID.randomUUID().toString(); //https://www.baeldung.com/java-uuid
        Categoria categoria = categoriaRepository.obtenerOCrearPorNombre(dto.getCategoria());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(dto.getContenido_multimedia());
        Contenido contenido = new Contenido(dto.getContenido(),contenido_multimedia);
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(), null, null);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate fechaOcurrencia =  LocalDate.parse(dto.getFecha(), formato);
        Contribuyente contribuyente = new Contribuyente(dto.getUsuario(), null, null, null);  //Decision de diseño. Consultar con grupo
        boolean anonimo = Boolean.parseBoolean(dto.getAnonimo());                                                         //Una vez que el hecho se trae a una coleccion
        LocalDate fecha_carga = LocalDate.now();                                                                          //dependiendo si es visible o no buscamos el/la
        List<Etiqueta> etiquetas = new ArrayList<>();                                                                     //en la base de datos con el usuario.

        //La otra opción es guardar el hecho con el contribuyente y despues si tiene que ser anonimo se lo mnadamos al agregador si el contribuyente. El tema es que si no
        //entendí mal cuando el contribuyente elegía anónimo tenia que ser anónimo en todos lados.
        Hecho hecho = new Hecho(idHecho, dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion, fecha_carga, OrigenCarga.FUENTE_DINAMICA,
                false, contribuyente, anonimo, etiquetas);
        hechoRepository.guardarHecho(hecho);
    }

}
