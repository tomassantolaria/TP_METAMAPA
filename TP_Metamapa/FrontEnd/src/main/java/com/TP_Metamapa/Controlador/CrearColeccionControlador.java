package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.Modelos.Consenso;
import com.TP_Metamapa.Modelos.OrigenCarga;
import com.TP_Metamapa.Servicio.CategoriaServicio;
import com.TP_Metamapa.Servicio.LocalidadServicio;
import com.TP_Metamapa.Servicio.PaisServicio;
import com.TP_Metamapa.Servicio.ProvinciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CrearColeccionControlador {

    @Autowired
    CategoriaServicio categoriaServicio;
    @Autowired
    PaisServicio paisServicio;
    @Autowired
    ProvinciaServicio provinciaServicio;
    @Autowired
    LocalidadServicio localidadServicio;


    @GetMapping("/admin/crear-coleccion")
    public String mostrarFormulario(Model model) {


        List<String> categorias = categoriaServicio.getCategoriasUnicas();
        List<String> paises = paisServicio.getPaisesUnicos();
        List<String> provincias = provinciaServicio.getProvinciasUnicas();
        List<String> localidades = localidadServicio.getLocalidadesUnicas();
        OrigenCarga[] origenes = OrigenCarga.values();
        Consenso[] criteriosConsenso = Consenso.values();

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
