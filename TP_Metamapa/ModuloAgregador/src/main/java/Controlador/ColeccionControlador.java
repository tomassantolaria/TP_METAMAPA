package Controlador;

import Servicio.ColeccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Modelos.DTOs.ColeccionDTO;

@RestController
public class ColeccionControlador {

    @Autowired
    private ColeccionServicio coleccionServicio;

    @PostMapping("/colecciones/crear")
    public void crearColeccion(@RequestBody ColeccionDTO coleccionDTO) {
        coleccionServicio.crearColeccion(coleccionDTO);
    }
    @DeleteMapping("/hechos/{id}")
    public void eliminarHecho(@PathVariable String id) {
        coleccionServicio.eliminarHecho(id);
    }

}
