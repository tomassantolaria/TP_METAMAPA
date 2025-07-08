package Controlador;

import Modelos.DTOs.HechoDTO;
import Servicios.impl.FuenteDemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo")
public class FuenteDemoControler{

    private final FuenteDemoService fuenteDemoService;

    public FuenteDemoControler(FuenteDemoService fuenteDemoService) {
        this.fuenteDemoService = fuenteDemoService;
    }

    //PARA OBTENER LOS HECHOS DE LA FUENTE DEMO
    @GetMapping("/hechos")
    public List<HechoDTO> obtenerHecho(){
        return fuenteDemoService.obtenerHecho();
    }
}
