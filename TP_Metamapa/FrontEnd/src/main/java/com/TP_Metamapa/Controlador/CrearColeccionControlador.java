package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.ColeccionDTOInput;
import com.TP_Metamapa.DTOS.CriterioDTO;
import com.TP_Metamapa.Modelos.Consenso;
import com.TP_Metamapa.Modelos.OrigenCarga;
import com.TP_Metamapa.Servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    ColeccionServicio coleccionServicio;


    @GetMapping("/admin/crear-coleccion")
    public String mostrarFormulario(Model model) {


        List<String> categorias = categoriaServicio.getCategoriasUnicas();
        List<String> paises = paisServicio.getPaisesUnicos();
        List<String> provincias = provinciaServicio.getProvinciasUnicas();
        List<String> localidades = localidadServicio.getLocalidadesUnicas();
        OrigenCarga[] origenes = OrigenCarga.values();
        Consenso[] criteriosConsenso = Consenso.values();

        ColeccionDTOInput coleccionForm = new ColeccionDTOInput();
        coleccionForm.setCriterio(new CriterioDTO());


        model.addAttribute("coleccionForm", coleccionForm);
        model.addAttribute("categorias", categorias);
        model.addAttribute("paises", paises);
        model.addAttribute("provincias", provincias);
        model.addAttribute("localidades", localidades);
        model.addAttribute("origenes", origenes);
        model.addAttribute("criteriosConsenso", criteriosConsenso);

        return "crearColeccion";
    }


    @PostMapping("/admin/crear-coleccion")
    public String procesarCrearColeccion(
            @ModelAttribute("coleccionForm") ColeccionDTOInput coleccionData,
            RedirectAttributes redirectAttributes
    ) {
        try {
            coleccionServicio.crear(coleccionData);

            redirectAttributes.addFlashAttribute("successMessage", "¡Colección creada con éxito!");
            return "redirect:/admin?tab=collections";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear la colección: " + e.getMessage());
            return "redirect:/admin/crear-coleccion";
        }
    }
}
