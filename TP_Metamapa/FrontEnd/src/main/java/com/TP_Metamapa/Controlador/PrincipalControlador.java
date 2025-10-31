package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.HechoDTO;
import com.TP_Metamapa.Servicio.HechoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PrincipalControlador {

    @Autowired
    HechoServicio hechoService;

    @GetMapping("/")
    public String paginaPrincipal(Model model) {
        List<HechoDTO> hechosDePrueba = hechoService.hechosRecientes();
//        List<HechoDTO> hechosDePrueba = new ArrayList<>();
//        HechoDTO hecho1 = new HechoDTO(1L, 101L, "Incendio en bosque", "Un gran incendio forestal afectó la zona sur.", "El incendio comenzó en horas de la tarde y se extendió rápidamente.", "imagen_incendio.jpg", "Desastre natural", LocalDateTime.of(2023, 5, 20, 16, 30), LocalDateTime.now(), "Bariloche", "Río Negro", "Argentina", -41.1335, -71.3103, "usuario123", "Juan", "Pérez", LocalDateTime.of(1990, 3, 15, 0, 0), false, true, "App móvil");
//        HechoDTO hecho2 = new HechoDTO(2L, 102L, "Marcha por los derechos laborales", "Manifestación pacífica en el centro.", "Cientos de personas se reunieron para reclamar mejoras salariales.", "video_marcha.mp4", "Protesta social", LocalDateTime.of(2024, 9, 1, 10, 0), LocalDateTime.now(), "Córdoba", "Córdoba", "Argentina", -31.4201, -64.1888, "usuario456", "María", "González", LocalDateTime.of(1985, 8, 22, 0, 0), true, true, "Sitio web");
//        HechoDTO hecho3 = new HechoDTO(3L, 103L, "Lanzamiento de satélite nacional", "Evento histórico en el ámbito tecnológico.", "Argentina lanzó su nuevo satélite de comunicaciones desde Cabo Cañaveral.", "foto_satélite.jpg", "Tecnología", LocalDateTime.of(2025, 4, 10, 14, 0), LocalDateTime.now(), "Cabo Cañaveral", "Florida", "Estados Unidos", 28.3922, -80.6077, "usuario789", "Lucía", "Martínez", LocalDateTime.of(1992, 12, 5, 0, 0), false, false, "Carga institucional");
//
//        hechosDePrueba.add(hecho1);
//        hechosDePrueba.add(hecho2);
//        hechosDePrueba.add(hecho3);
        // Suponiendo que tu clase Hecho tiene un constructor o setters
        // Reemplaza esto con los campos reales de tu DTO/Clase
        model.addAttribute("hechos", hechosDePrueba);
        return "principal";
    }

}