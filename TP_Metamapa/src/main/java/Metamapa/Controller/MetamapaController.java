package Metamapa.Controller;

import Metamapa.Service.MetamapaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Domain.HechoDTO;
import Domain.ContribuyenteDTO;

import java.util.List;


@RestController
@RequestMapping("/Metamapa")

public class MetamapaController {

    private final MetamapaService metamapaService;

    public MetamapaController(MetamapaService metamapaService) {
        this.metamapaService = metamapaService;
    }

    @RequestMapping("coleccion/{id}/filtrar")
    public List<HechoDTO> coleccionFiltrada(@PathVariable Long id, @RequestBody CriterioDTO criterios) {
        return metamapaService.filtrarHechos(criterios, id);
    }

    @PostMapping("/hechos")
    public ResponseEntity<String> cargarHecho(@RequestBody HechoDTO hechoDTO){
        metamapaService.cargarHecho(hechoDTO);
        return ResponseEntity.ok("Hechos creado correctamente");
    }


    @PostMapping("/registrarse")
    public ResponseEntity<String> registrarse(@RequestBody ContribuyenteDTO contribuyente){
        metamapaService.registrar(contribuyente);
        return ResponseEntity.ok("Registro registrado exitosamente");
    }
}