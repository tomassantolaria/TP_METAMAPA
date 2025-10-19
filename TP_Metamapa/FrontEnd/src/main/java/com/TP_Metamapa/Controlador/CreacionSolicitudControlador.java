package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.*;
import com.TP_Metamapa.Modelos.*;
import com.TP_Metamapa.Servicio.*;
import com.TP_Metamapa.Servicio.NavegacionServicio;
import com.TP_Metamapa.Servicio.SolicitudServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

/**
 * Controlador para manejar las vistas de Solicitudes en el Módulo Público.
 */
@Controller
public class CreacionSolicitudControlador {

    // Nota: Se asume que estos repositorios/servicios están correctamente inyectados mediante un constructor o @Autowired.
    // Para simplificar, mantengo la estructura del código anterior.
    @Autowired
    NavegacionServicio navegadorServicio;

    @Autowired
    SolicitudServicio solicitudServicio;


    @GetMapping("/solicitarElimiacion/{id}")
    public String mostrarFormularioSolicitud(@PathVariable("id") Long idHecho, Model model) {
        //HechoDTO hechoDTO = navegadorServicio.obtenerHechoPorId(idHecho);
        HechoDTO hechoDTO = new HechoDTO(1L, 101L, "Incendio en bosque", "Un gran incendio forestal afectó la zona sur.", "El incendio comenzó en horas de la tarde y se extendió rápidamente.", "imagen_incendio.jpg", "Desastre natural", LocalDateTime.of(2023, 5, 20, 16, 30), LocalDateTime.now(), "Bariloche", "Río Negro", "Argentina", -41.1335, -71.3103, "usuario123", "Juan", "Pérez", LocalDateTime.of(1990, 3, 15, 0, 0), false, true, "App móvil");
        model.addAttribute("hecho", hechoDTO);
        model.addAttribute("solicitudDTO", new SolicitudDTOInput("", idHecho));

        return "crearSolicitud";
    }

    @PostMapping("/crearSolicitud")
public String crearSolicitud(@ModelAttribute("solicitudDTO") SolicitudDTOInput solicitudDTO, RedirectAttributes redirectAttributes) {
    try {
        solicitudServicio.crearSolicitud(solicitudDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Su solicitud de eliminación ha sido enviada con éxito.");

        return "redirect:/navegar";

    } catch (RuntimeException e) {
        String errorMessage = "Error al procesar su solicitud.";
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        return "redirect:/solicitarElimiacion/" + solicitudDTO.getIdHecho();
    }
}
}