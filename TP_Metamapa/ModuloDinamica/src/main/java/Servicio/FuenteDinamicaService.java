package Servicio;

import Repositorio.FuenteDinamicaRepository;
import Modelos.Entidades.*;
import Modelos.DTOs.*;
import Repositorio.*;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class FuenteDinamicaService {

    private final CategoriaRepository categoriaRepository;
    private final FuenteDinamicaRepository fuenteDinamicaRepository;

    public FuenteDinamicaService(CategoriaRepository categoriaRepository, FuenteDinamicaRepository fuenteDinamicaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.fuenteDinamicaRepository = fuenteDinamicaRepository;
    }

    public void crearHecho(HechoDTO dto) {
        // Obtener o crear la categoría
        Categoria categoria = categoriaRepository.obtenerOCrearPorNombre(dto.getCategoria());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(dto.getContenido_multimedia());
        Contenido contenido = new Contenido(dto.getContenido(),contenido_multimedia);
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(), null, null);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate fecha =  LocalDate.parse(dto.getFecha(), formato);
        Contribuyente contribuyente = //new Contribuyente() - Pensar lógica de esta parte. Un contribuyente cuando cree un hecho que va a agregar de su informacion en el formulario
        boolean anonimo = Boolean.parseBoolean(dto.getAnonimo());
        LocalDate fecha_carga = LocalDate.now();

        Hecho hecho = Hecho.getInstance(
                dto.getTitulo(),
                dto.getDescripcion(),
                contenido,
                categoria,
                fecha,
                ubicacion,
                fecha_carga,
                OrigenCarga.FUENTE_DINAMICA,
                false,
                dto.getUsuario(),
                anonimo
        );
        fuenteDinamicaRepository.guardarHecho(hecho);
    }

}
