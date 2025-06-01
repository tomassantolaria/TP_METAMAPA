package Dinamica.Service;

import Dinamica.Repository.FuenteDinamicaRepository;
import Domain.*;
import Domain.Hecho;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class FuenteDinamicaService {

    private final CategoriaRepository categoriaRepository;

    public FuenteDinamicaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private final FuenteDinamicaRepository fuenteDinamicaRepository;

    public FuenteDinamicaService(FuenteDinamicaRepository fuenteDinamicaRepository) {
        this.fuenteDinamicaRepository = fuenteDinamicaRepository;
    }

    public void crearHecho(HechoDTO dto) {
        // Obtener o crear la categoría
        Categoria categoria = categoriaRepository.obtenerOCrearPorNombre(dto.getCategoria());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(dto.getContenido_multimedia());
        Contenido contenido = new Contenido(dto.getContenido(),contenido_multimedia);
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(),dto.getLatitud(),dto.getLongitud());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha =  LocalDate.parse(dto.getFecha(), formato);

        boolean anonimo = Boolean.parseBoolean(dto.getAnonimo());
        LocalDate hoy = LocalDate.now();

        Hecho hecho = Hecho.getInstance(
                dto.getTitulo(),
                dto.getDescripcion(),
                contenido,
                categoria,
                fecha,
                ubicacion,
                hoy,
                OrigenCarga.FUENTE_DINAMICA,
                false, // visible por defecto
                dto.getUsuario(),
                anonimo
        );
        fuenteDinamicaRepository.guardarHecho(hecho);
    }

}
