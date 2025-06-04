package Controlador;

import Servicio.AgregadorServicio;
import org.springframework.http.ResponseEntity;
import Domain.HechoDTO;
import Domain.ContribuyenteDTO;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/Metamapa")

public class AgregadorControlador {

    private final AgregadorServicio agregadorServicio;

    public AgregadorControlador(AgregadorServicio agregadorServicio) {
        this.agregadorServicio = agregadorServicio;
    }

    @RequestMapping("coleccion/{id}/filtrar")
    public List<HechoDTOOutput> coleccionFiltrada(@PathVariable Long id, @RequestBody CriterioDTO criterios) {
        return agregadorServicio.filtrarHechos(criterios, id);
    }

    @PostMapping("/registrarse")
    public ResponseEntity<String> registrarse(@RequestBody ContribuyenteDTO contribuyente){
        agregadorServicio.registrar(contribuyente);
        return ResponseEntity.ok("Registro registrado exitosamente");
    }
}