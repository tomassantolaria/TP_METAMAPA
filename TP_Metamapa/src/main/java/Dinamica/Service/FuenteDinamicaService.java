package Dinamica.Service;

import Domain.*;
import Dinamica.Controller.HechoContribuyenteDTO;

import java.util.Date;

public class FuenteDinamicaService {

    private final CategoriaRepository categoriaRepository;

    public FuenteDinamicaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    //Yo creo que la logica de revisar si un hecho ya existe se tiene que hacer aca porque esto es lo que va a interactuar con el repository
    public void crearHechoDTO(HechoContribuyenteDTO dto) {
        // Obtener o crear la categoría
        Categoria categoria = categoriaRepository.obtenerOCrearPorNombre(dto.getCategoria());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(dto.getContenido_multimedia());
        Contenido contenido = new Contenido(dto.getContenido(),contenido_multimedia);
        Ubicacion ubicacion = new Ubicacion(dto.getLugar(),dto.getLatitud(),dto.getLongitud());

        HechoContribuyente hechoContribuyente = HechoContribuyente.getInstance(
                dto.getTitulo(),
                dto.getDescripcion(),
                contenido,
                categoria,
                dto.getFecha(),
                ubicacion,
                new Date(),
                OrigenCarga.FUENTE_DINAMICA,
                true, // visible por defecto
                dto.getTitulo(), //Tiran error por el getInstance
                dto.getApellido(),
                dto.getFecha_Nacimiento(),
                dto.getAnonimo()
        );
    }
    public
}
