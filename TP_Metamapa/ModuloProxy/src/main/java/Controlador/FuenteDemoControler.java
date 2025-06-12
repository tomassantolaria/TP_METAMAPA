package Controlador;

import Modelos.Entidades.Hecho;
import Servicios.impl.FuenteDemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class FuenteDemoControler{

    private final FuenteDemoService fuenteDemoService;

    public FuenteDemoControler(FuenteDemoService fuenteDemoService) {
        this.fuenteDemoService = fuenteDemoService;
    }

    //PARA OBTENER LOS HECHOS DE LA FUENTE DEMO
    @GetMapping("/hechos")
    public List<Hecho> obtenerHecho(){
        return fuenteDemoService.obtenerHecho();
    }
}
