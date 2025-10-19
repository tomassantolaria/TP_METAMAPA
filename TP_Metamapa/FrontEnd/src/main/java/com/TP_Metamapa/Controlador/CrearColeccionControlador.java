package com.TP_Metamapa.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CrearColeccionControlador {

    // GET → muestra el formulario
    @GetMapping("/admin/crear-coleccion")
    public String mostrarFormulario(Model model) {

        // Simulación de datos que normalmente vendrían de un servicio
        List<String> categorias = new ArrayList<>();
        categorias.add("Inundaciones");
        categorias.add("Incendios");

        List<String> paises = new ArrayList<>();
        paises.add("Argentina");
        paises.add("Chile");
        paises.add("Uruguay");

        List<String> provincias = new ArrayList<>();
        provincias.add("Buenos Aires");
        provincias.add("Córdoba");
        provincias.add("Mendoza");

        List<String> localidades = new ArrayList<>();
        localidades.add("Rosario");
        localidades.add("La Plata");
        localidades.add("Mar del Plata");

        List<String> origenes = new ArrayList<>();
        origenes.add("Manual");
        origenes.add("Automático");

        List<String> criteriosConsenso = new ArrayList<>();
        criteriosConsenso.add("MAYORIA SIMPLE");
        criteriosConsenso.add("ABSOLUTA");

        // Se agregan al modelo para que el HTML pueda mostrarlas con Thymeleaf si lo usás
        model.addAttribute("categorias", categorias);
        model.addAttribute("paises", paises);
        model.addAttribute("provincias", provincias);
        model.addAttribute("localidades", localidades);
        model.addAttribute("origenes", origenes);
        model.addAttribute("criteriosConsenso", criteriosConsenso);

        return "crearColeccion";
    }

    // POST → procesa los datos del formulario
    @PostMapping("/admin/crear-coleccion")
    public String procesarFormulario(
            @RequestParam String titulo,
            @RequestParam String descripcion,
            @RequestParam(required = false) String criterioConsenso,
            @RequestParam(required = false, name = "titulo-criterio") String tituloCriterio,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String provincia,
            @RequestParam(required = false) String localidad,
            @RequestParam(required = false, name = "origen_carga") String origenCarga,
            Model model
    ) {
        // Mensaje de éxito que podrías mostrar en la vista
        model.addAttribute("exito", "Colección creada con éxito.");

        // Volvemos a mostrar el formulario (o podrías redirigir)
        return "crearColeccion";
    }
}
