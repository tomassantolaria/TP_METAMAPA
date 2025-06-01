package Metamapa.Controller;

import Metamapa.Service.MetamapaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Dinamica.Controller.HechoContribuyenteDTO;

import java.util.List;


@RestController
@RequestMapping("/Metamapa")

public class MetamapaController {

    private final MetamapaService metamapaService;

    public MetamapaController(MetamapaService metamapaService) {
        this.metamapaService = metamapaService;
    }

    @RequestMapping("coleccion/{id}/filtrar")
    public List<HechoContribuyenteDTO> coleccionFiltrada(@PathVariable Long id, @RequestBody CriterioDTO criterios) {
        return metamapaService.filtrarHechos(criterios, id);
    }

    @PostMapping("/hechos")
    public ResponseEntity<String> cargarHecho(@RequestBody String hechoDTO) {}
    }



}
