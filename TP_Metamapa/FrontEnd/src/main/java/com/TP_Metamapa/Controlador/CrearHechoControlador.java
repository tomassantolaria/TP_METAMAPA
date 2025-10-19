package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.Servicio.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CrearHechoControlador {

    // GET → muestra el formulario
    @Autowired
    CategoriaServicio categoriaServicio;
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
        return "creaHecho";
    }
}