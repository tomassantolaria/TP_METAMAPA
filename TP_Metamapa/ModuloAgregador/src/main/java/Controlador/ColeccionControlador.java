package Controlador;

import Servicio.ColeccionServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Controlador.Modelos.DTOs.ColeccionDTO;

public class ColeccionControlador {
    private final ColeccionServicio coleccionServicio;

    public ColeccionControlador(ColeccionServicio coleccionServicio) {
        this.coleccionServicio = coleccionServicio;
    }

    @PostMapping("/colecciones/crear")
    public void crearColeccion(@RequestBody ColeccionDTO coleccionDTO) {
        coleccionServicio.crearColeccion(coleccionDTO);
    }
}
