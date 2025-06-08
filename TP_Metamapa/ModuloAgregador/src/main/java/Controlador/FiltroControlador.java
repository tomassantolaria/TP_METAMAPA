package Controlador;

import Controlador.Modelos.DTOs.CriterioDTO;
import Controlador.Modelos.DTOs.HechoDTOOutput;
import Servicio.FiltroServicio;
import org.springframework.http.ResponseEntity;
import Controlador.Modelos.DTOs.ContribuyenteDTOInput;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/Metamapa")

public class FiltroControlador {

    private final FiltroServicio filtroServicio;

    public FiltroControlador(FiltroServicio filtroServicio) {
        this.filtroServicio = filtroServicio;
    }

    @RequestMapping("coleccion/{id}/filtros")
    public List<HechoDTOOutput> coleccionFiltrada(@PathVariable String id, @RequestBody CriterioDTO criterios) {
        if(criterios == null)
            return filtroServicio.mostrarTodosLosHechosDe(id);
        else
        return filtroServicio.filtrarHechosDe(id, criterios);
    }

    @RequestMapping("hechos/filtros")
    public List<HechoDTOOutput> hechosFiltrados(@RequestBody CriterioDTO criterios) {
        if(criterios == null)
            return filtroServicio.mostrarTodosLosHechos();
        else
            return filtroServicio.filtrar(criterios);
    }


}