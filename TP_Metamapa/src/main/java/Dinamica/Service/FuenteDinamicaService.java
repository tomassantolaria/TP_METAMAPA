package Dinamica.Service;

import Domain.*;
import Dinamica.Controller.HechoDTO;

import java.util.Date;

public class FuenteDinamicaService {

    private final CategoriaRepository categoriaRepository;

    public FuenteDinamicaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void crearHechoDesdeDTO(HechoDTO dto) {
        // Obtener o crear la categoría
        Categoria categoria = categoriaRepository.obtenerOCrearPorNombre(dto.getCategoria());
        //OrigenCarga origen = OrigenCarga.valueOf(dto.getOrigen_carga().toUpperCase());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(dto.getContenido_multimedia());
        Contenido contenido = new Contenido(dto.getContenido(),contenido_multimedia);
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(),dto.getLatitud(),dto.getLongitud());
        Date fecha = new Date(dto.getFecha());

        Hecho hecho = Hecho.getInstance(
                dto.getTitulo(),
                dto.getDescripcion(),
                contenido,
                categoria,
                fecha,
                ubicacion,
                new Date(),
                OrigenCarga.FUENTE_DINAMICA,
                true // visible por defecto
        );

        // Podés agregar lógica adicional si querés mostrarlo, registrarlo, etc.
    }
}
