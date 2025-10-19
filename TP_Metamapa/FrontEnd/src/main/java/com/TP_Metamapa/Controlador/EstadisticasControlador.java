package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.ColeccionDTO;
//import com.TP_Metamapa.DTOS.StatsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;


@Controller
public class EstadisticasControlador {
//
//    // @Autowired
//    // EstadisticasServicio estadisticasServicio; // En el futuro, la lógica de API irá aquí
//
//    @GetMapping("/estadisticas")
//    public String mostrarEstadisticas(
//            @RequestParam(required = false) Long coleccionId,
//            @RequestParam(required = false) String categoria,
//            Model model
//    ) {
//        String provinciaPorCategoria = estadisticasServicio.obtenerProvinciaConMasHechos(coleccionId);
//        String = estadisticasServicio.obtenerCategoriaConMasHechos();
//
//
//
//        model.addAttribute("estadisticas", estadisticas);
//        model.addAttribute("colecciones", colecciones);
//        model.addAttribute("coleccionesEstadisticas", coleccionesEstadisticas);
//        model.addAttribute("provinciaPorCategoria", provinciaPorCategoria);
//        model.addAttribute("mejorHora", mejorHora);
//        model.addAttribute("coleccionSeleccionada", coleccionSeleccionada);
//        model.addAttribute("categoriaSeleccionada", categoriaSeleccionada);
//
//        return "estadisticas";
//    }
}
