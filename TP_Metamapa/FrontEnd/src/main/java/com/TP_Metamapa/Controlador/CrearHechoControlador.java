package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.ColeccionDTO;
import com.TP_Metamapa.DTOS.HechoDTO;
import com.TP_Metamapa.Servicio.CategoriaServicio;
import com.TP_Metamapa.Servicio.HechoServicio;
import com.TP_Metamapa.Servicio.NavegacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CrearHechoControlador {

    // GET → muestra el formulario
    @Autowired
    CategoriaServicio categoriaServicio;
    @Autowired
    NavegacionServicio navegacionServicio;

    @GetMapping("/crear-hecho")
    public String mostrarFormulario(Model model) {
        // List<String> categorias = categoriaServicio.getCategoriasUnicas();
        List<String> categorias = new ArrayList<>();
        categorias.add("Política");
        categorias.add("Cultura");
        categorias.add("Deportes");
        categorias.add("Otra");
        model.addAttribute("categorias", categorias);
        return "crearHecho"; // corresponde al archivo crear-hecho.html
    }

    // POST → procesa el formulario
    @PostMapping("/crear-hecho")
    public String procesarFormulario(
            @RequestParam String titulo,
            @RequestParam String descripcion,
            @RequestParam(required = false) String contenido,
            @RequestParam String categoria,
            @RequestParam String fechaAcontecimiento,
            @RequestParam String localidad,
            @RequestParam String provincia,
            @RequestParam String pais,
            Model model) {

        // Acá después vas a guardar en la base, pero por ahora solo mostramos mensaje
        model.addAttribute("exito", "Hecho enviado con éxito ");

        // Volvemos a mostrar el formulario
        return "crearHecho";// return "redirect:/hechos/" + hecho.getId();
    }

    @GetMapping("ver-hecho/{id}")
    public String verHecho(@PathVariable Long id, Model model) {
        // habría que hacer en lugar del service, una llamada al back
        HechoDTO hechoOpt = navegacionServicio.obtenerHechoPorId(id);
//        Optional<HechoDTO> hechoOpt = Optional.of(
//                new HechoDTO(1L, 101L, "Incendio en bosque", "Un gran incendio forestal afectó la zona sur.", "El incendio comenzó en horas de la tarde y se extendió rápidamente.", "imagen_incendio.jpg", "Desastre natural", LocalDateTime.of(2023, 5, 20, 16, 30), LocalDateTime.now(), "Bariloche", "Río Negro", "Argentina", -41.1335, -71.3103, "usuario123", "Juan", "Pérez", LocalDateTime.of(1990, 3, 15, 0, 0), false, true, "App móvil")
//        );

        if ( hechoOpt == null ) {
            model.addAttribute("errorMessage", "El Hecho con ID " + id + " no fue encontrada.");
            return "error/404";
        }


        model.addAttribute("hecho", hechoOpt);
        return "verHecho";
    }
}