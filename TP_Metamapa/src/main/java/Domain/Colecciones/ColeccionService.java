package Domain.Colecciones;

public class ColeccionService {

    private final ColeccionRepository coleccionRepository;

    public ColeccionService(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    public Coleccion obtenerOCriarExcepcion(Long id) {
        return coleccionRepository.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Colección no encontrada"));
    }

    // Acá podés agregar más lógica de negocio sobre colecciones si hace falta
}

