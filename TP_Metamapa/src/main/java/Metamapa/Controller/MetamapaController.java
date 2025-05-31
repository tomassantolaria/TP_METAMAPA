package Metamapa.Controller;

import Metamapa.Metamapa;
import Metamapa.Service.MetamapaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Dinamica.Controller.HechoDTO;
import Domain.Busqueda;
import java.util.List;


@RestController
@RequestMapping("/Metamapa")

public class MetamapaController {

    private final MetamapaService metamapaService;

    public MetamapaController(MetamapaService metamapaService) {
        this.metamapaService = metamapaService;
    }

    @RequestMapping("/coleccion/filtrar")
    public List<HechoDTO> coleccion (@RequestBody CriterioDTO criterios) {
        List<HechoDTO> resultados = metamapaService.filtrarHechos(criterios);
        return ResponseEntity.ok(resultados);
    }

    @PostMapping("/hechos")
    public ResponseEntity<String> cargarHecho(@RequestBody String hechoDTO) {
        return 0;
    }
}
