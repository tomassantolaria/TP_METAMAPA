package Controlador.Colecciones;

import org.springframework.stereotype.Service;

@Service
public class ColeccionServicio {

    private final ColeccionRepositorio coleccionRepositorio;

    public ColeccionServicio(ColeccionRepositorio coleccionRepositorio) {
        this.coleccionRepositorio = coleccionRepositorio;
    }

    public Coleccion obtenerOCriarExcepcion(Long id) {
        try {
            return coleccionRepositorio.obtenerPorId(id);
        } catch (Exception e) {
            throw new RuntimeException("Colección no encontrada");
        }
    }

    // Acá podés agregar más lógica de negocio sobre colecciones si hace falta
}

