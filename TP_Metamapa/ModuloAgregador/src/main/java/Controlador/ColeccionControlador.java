package Controlador;

import Servicio.ColeccionServicio;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Modelos.DTOs.ColeccionDTO;

public class ColeccionControlador {
    private final ColeccionServicio coleccionServicio;

    public ColeccionControlador(ColeccionServicio coleccionServicio) {
        this.coleccionServicio = coleccionServicio;
    }

    @PostMapping("/colecciones/crear")
    public void crearColeccion(@RequestBody ColeccionDTO coleccionDTO) {
        coleccionServicio.crearColeccion(coleccionDTO);
    }
    @DeleteMapping("/hechos/{id}")
    public void eliminarHecho(@PathVariable String id) {
        coleccionServicio.eliminarHecho(id);
    }

}
