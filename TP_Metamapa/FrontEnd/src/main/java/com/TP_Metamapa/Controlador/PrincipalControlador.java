package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.HechoDTO;
import com.TP_Metamapa.Servicio.HechoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrincipalControlador {

    @Autowired
    HechoServicio hechoService;

    @GetMapping("/")
    public String paginaPrincipal(Model model) {
        //List<HechoDTO> hechosRecientes = hechoService.hechosRecientes();

        List<HechoDTO> hechosDePrueba = new ArrayList<>();

        // Suponiendo que tu clase Hecho tiene un constructor o setters
        // Reemplaza esto con los campos reales de tu DTO/Clase
        //hechosDePrueba.add(new Hecho(1L, "Hecho de Prueba 1", "Esta es la descripción del primer hecho de prueba para ver el diseño.", "Cultura", "Buenos Aires"));
        //hechosDePrueba.add(new Hecho(2L, "Otro Hecho Interesante", "Descripción del segundo hecho. Podemos ver cómo se alinea la grilla.", "Historia", "Córdoba"));
        model.addAttribute("hechos", hechosDePrueba);
        return "principal";
    }
}