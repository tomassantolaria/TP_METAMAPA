package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.ColeccionDTO;
import com.TP_Metamapa.DTOS.HechoDTO;
import com.TP_Metamapa.DTOS.HechoDTOInput;
import com.TP_Metamapa.DTOS.HechoFormDTO;
import com.TP_Metamapa.Modelos.OrigenCarga;
import com.TP_Metamapa.Servicio.CategoriaServicio;
import org.springframework.security.core.Authentication;
import com.TP_Metamapa.Servicio.HechoServicio;
import com.TP_Metamapa.Servicio.NavegacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    HechoServicio hechoServicio;

    @GetMapping("/crear-hecho")
    public String mostrarFormulario(Model model) {
        List<String> categorias = categoriaServicio.getCategoriasUnicas();
        model.addAttribute("hechoForm", new HechoFormDTO());
        model.addAttribute("categorias", categorias);
        return "crearHecho";
    }

    // POST → procesa el formulario
    @PostMapping("/crear-hecho")
    public String procesarCrearHecho(
            @ModelAttribute("hechoForm") HechoFormDTO hechoFormData, // Recibe datos del form
            @RequestParam(value = "multimediaFile", required = false) MultipartFile multimediaFile, // Recibe el archivo
            Authentication authentication, // Obtiene el usuario logueado
            RedirectAttributes redirectAttributes,
            Model model // Para volver a mostrar el form si hay error
    ) {
        String imageUrl = null;
        try {
            // 1. Guardar la imagen localmente (si existe) y obtener su URL relativa
            imageUrl = hechoServicio.guardarMultimediaLocalmente(multimediaFile);

            // 2. Crear el DTO que se enviará al backend
            HechoDTOInput hechoParaBackend = new HechoDTOInput(hechoFormData.getTitulo(), hechoFormData.getDescripcion(), hechoFormData.getContenidoAdicional(), imageUrl, hechoFormData.getCategoria(), hechoFormData.getFechaAcontecimiento(), hechoFormData.getLocalidad(), hechoFormData.getProvincia(), hechoFormData.getPais(), hechoFormData.getLatitud(), hechoFormData.getLongitud(), "admin", hechoFormData.isAnonimo());
            //TODO: CREAR LOS CAMPOS AUTENTICATHION QUE SEAN USUARIO NOMBRE APELLIDO Y FECHA DE NACIMIENTO Y AGREGAR ACA
            if ("Otra".equals(hechoFormData.getCategoria()) && hechoFormData.getCustomCategoria() != null && !hechoFormData.getCustomCategoria().isEmpty()) {
                hechoParaBackend.setCategoria(hechoFormData.getCustomCategoria());
            } else {
                hechoParaBackend.setCategoria(hechoFormData.getCategoria());
            }

            hechoServicio.enviarHechoAlBackend(hechoParaBackend);

            redirectAttributes.addFlashAttribute("successMessage", "¡Hecho creado con éxito!");
            return "redirect:/";

        } catch (Exception e) {
            System.err.println("Error procesando creación de hecho: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error al crear el hecho: " + e.getMessage());
            List<String> categorias = categoriaServicio.getCategoriasUnicas();
            model.addAttribute("categorias", categorias);
            // El 'hechoForm' ya está en el modelo gracias a @ModelAttribute, así que los campos se rellenan
            return "crearHecho";
        }
    }

    @GetMapping("ver-hecho/{id}")
    public String verHecho(@PathVariable Long id, Model model) {
        // habría que hacer en lugar del service, una llamada al back
        Optional<HechoDTO> hechoOpt = navegacionServicio.obtenerHechoPorId(id);
        if ( hechoOpt.isPresent()){
            model.addAttribute("hecho", hechoOpt.get()
            );
            model.addAttribute("origenDinamica", OrigenCarga.FUENTE_DINAMICA
            );
            return "verHecho";
        }else{
            model.addAttribute("errorMessage", "El hecho con ID " + id + " no fue encontrado.");
            // Devolvemos el nombre de tu vista 404
            return "error/404";
        }
    }
}