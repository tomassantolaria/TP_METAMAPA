package com.TP_Metamapa.Controlador;


import com.TP_Metamapa.DTOS.*;
import com.TP_Metamapa.Modelos.TipoFuente;
import com.TP_Metamapa.Servicio.ColeccionServicio;
import com.TP_Metamapa.Servicio.FuenteProxyServicio;
import com.TP_Metamapa.Servicio.HechoServicio;
import com.TP_Metamapa.Servicio.SolicitudServicio;
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
public class AdminControlador {

    @Autowired
    ColeccionServicio coleccionServicio;
    @Autowired
    SolicitudServicio solicitudServicio;
    @Autowired
    FuenteProxyServicio fuenteProxyServicio;
    @Autowired
    HechoServicio hechoServicio;



    @GetMapping("/admin")
    public String panelDeAdmin(
            @RequestParam(name = "tab", defaultValue = "collections") String activeTab,
            Model model
    ) {
        List<ColeccionDTO> colecciones = new ArrayList<>();
        List<SolicitudDTO> solicitudes = new ArrayList<>();
        List<FuentesDTO> fuentes = new ArrayList<>();
        TipoFuente[] tipoFuentes = TipoFuente.values();
        //List<HechoDTO> hechosDePrueba = new ArrayList<>();
        //HechoDTO hecho1 = new HechoDTO(1L, 101L, "Incendio en bosque", "Un gran incendio forestal afectó la zona sur.", "El incendio comenzó en horas de la tarde y se extendió rápidamente.", "imagen_incendio.jpg", "Desastre natural", LocalDateTime.of(2023, 5, 20, 16, 30), LocalDateTime.now(), "Bariloche", "Río Negro", "Argentina", -41.1335, -71.3103, "usuario123", "Juan", "Pérez", LocalDateTime.of(1990, 3, 15, 0, 0), false, true, "App móvil");
        //HechoDTO hecho2 = new HechoDTO(2L, 102L, "Marcha por los derechos laborales", "Manifestación pacífica en el centro.", "Cientos de personas se reunieron para reclamar mejoras salariales.", "video_marcha.mp4", "Protesta social", LocalDateTime.of(2024, 9, 1, 10, 0), LocalDateTime.now(), "Córdoba", "Córdoba", "Argentina", -31.4201, -64.1888, "usuario456", "María", "González", LocalDateTime.of(1985, 8, 22, 0, 0), true, true, "Sitio web");
        //HechoDTO hecho3 = new HechoDTO(3L, 103L, "Lanzamiento de satélite nacional", "Evento histórico en el ámbito tecnológico.", "Argentina lanzó su nuevo satélite de comunicaciones desde Cabo Cañaveral.", "foto_satélite.jpg", "Tecnología", LocalDateTime.of(2025, 4, 10, 14, 0), LocalDateTime.now(), "Cabo Cañaveral", "Florida", "Estados Unidos", 28.3922, -80.6077, "usuario789", "Lucía", "Martínez", LocalDateTime.of(1992, 12, 5, 0, 0), false, false, "Carga institucional");


        switch (activeTab) {
            case "collections":
                colecciones = coleccionServicio.getColecciones();
                /*
                hechosDePrueba.add(hecho1);
                hechosDePrueba.add(hecho2);
                hechosDePrueba.add(hecho3);

                ColeccionDTO c1 = new ColeccionDTO(1L, "Avistamientos de Fauna", "Hechos relacionados con animales.", hechosDePrueba, null, "MAYORIA_SIMPLE", null);
                ColeccionDTO c2 = new ColeccionDTO(2L, "Sucesos Históricos", "Eventos importantes de la historia.", null, null,"MAYORIA_SIMPLE",null);
                ColeccionDTO c3 =  new ColeccionDTO(3L, "Incendios Forestales", "Reportes de incendios en zonas naturales.", null, null,null,null);
                coleccionesDePrueba.add(c1);
                coleccionesDePrueba.add(c2);
                coleccionesDePrueba.add(c3);
*/
                break;
            case "requests":
                  solicitudes = solicitudServicio.obtenerPendientes();
//                SolicitudDTO s1 = new SolicitudDTO(1L, "Incluir colección de Avistamientos de Fauna", hecho1, LocalDateTime.now());
//                SolicitudDTO s2 = new SolicitudDTO(2L, "Modificar colección de Sucesos Históricos", hecho2, LocalDateTime.now());
//                solicitudes.add(s1);
//                solicitudes.add(s2);
                break;
            case "static":
                // No se necesitan datos adicionales para esta pestaña
                break;
            case "proxy":
                fuentes = fuenteProxyServicio.obtenerTodas();
                //fuentes.add(new FuentesDTO("http://ejemplo.com/api", TipoFuente.METAMAPA));

                break;
            default:
                break;
        }
        model.addAttribute("fuentesProxy", fuentes);
        model.addAttribute("colecciones", colecciones);
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("tiposFuente", tipoFuentes);
        model.addAttribute("activeTab", activeTab);

        return "admin"; // Siempre renderiza la misma vista principal
    }

    // NUEVO MÉTODO para procesar la subida del archivo CSV
    @PostMapping("/admin/cargar-csv")
    public String cargarArchivoCsv(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        // Verificación 1: que el archivo no esté vacío
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Por favor, seleccione un archivo para cargar.");
            return "redirect:/admin?tab=static";
        }

        // Verificación 2: que sea un archivo CSV
        if (!"text/csv".equals(file.getContentType())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: El archivo debe ser de formato CSV.");
            return "redirect:/admin?tab=static";
        }

        try {
            // Aquí iría la lógica para leer y procesar el archivo
            //estaticaServicio.procesarArchivoCsv(file); //TODO: HACER ESTO E IMPLEMENTARLO EN EL BACKEND
            redirectAttributes.addFlashAttribute("successMessage", "¡Archivo '" + file.getOriginalFilename() + "' cargado exitosamente!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al procesar el archivo: " + e.getMessage());
        }
        // Redirigimos de vuelta a la misma pestaña del admin
        return "redirect:/admin?tab=static";
    }


    @PostMapping("/admin/crear-proxy")
    public String crearFuenteProxy(
            @RequestParam String url,
            @RequestParam TipoFuente tipoFuente,
            RedirectAttributes redirectAttributes
    ) {
        // Verificación simple para la URL
        if (url == null || url.isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "La URL no puede estar vacía.");
            return "redirect:/admin?tab=proxy";
        }

        try {
            fuenteProxyServicio.crear(url, tipoFuente);

            System.out.println("Nueva fuente recibida: URL=" + url + ", Tipo=" + tipoFuente);

            redirectAttributes.addFlashAttribute("successMessage", "¡Fuente Proxy '" + url + "' creada con éxito!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear la fuente: " + e.getMessage());
        }

        return "redirect:/admin?tab=proxy";
    }

    @PostMapping("/admin/eliminar-coleccion/{id}")
    public String eliminarColeccion(
            @PathVariable Long id
    ) {
        coleccionServicio.eliminarColeccion(id);
        return "redirect:/admin?tab=collections";
    }

    @PostMapping("/admin/aceptar-solicitud/{id}")
    public String aceptarSolicitud(
            @PathVariable Long id
    ) {
        solicitudServicio.aceptarSolicitud(id);
        return "redirect:/admin?tab=requests";
    }

    @PostMapping("/admin/rechazar-solicitud/{id}")
    public String rechazarSolicitud(
            @PathVariable Long id
    ) {
        solicitudServicio.rechazarSolicitud(id);
        return "redirect:/admin?tab=requests";
    }

    @GetMapping("/admin/ver-coleccion/{id}")
    public String verColeccion(@PathVariable("id") Long id, Model model) {

        Optional<ColeccionDTO> coleccionOpt = coleccionServicio.obtenerColeccion(id);

        if (coleccionOpt.isPresent() ) {
            model.addAttribute("coleccion", coleccionOpt.get());
            return "verColeccion";
        }

        model.addAttribute("errorMessage", "La colección con ID " + id + " no fue encontrada.");
        return "error/404";
    }

    @GetMapping("/admin/editar-coleccion/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<ColeccionDTO> coleccion = coleccionServicio.obtenerColeccion(id);
        ConsensoDTO consensoDTO = new ConsensoDTO();
        if (coleccion.isPresent() ) {
            model.addAttribute("consensoDTO", consensoDTO);
            model.addAttribute("coleccion", coleccion.get());
            return "editarColeccion";
        }else{
        model.addAttribute("errorMessage", "La colección con ID " + id + " no fue encontrada.");
        return "error/404";
        }
    }

    @PostMapping("/admin/editar-coleccion/{id}/consenso")
    public String actualizarConsenso(@PathVariable Long id,
                                     @ModelAttribute("consensoDTO") ConsensoDTO consensoDTO,
                                     RedirectAttributes redirectAttributes) {
        try {
            coleccionServicio.actualizarColeccion(id, consensoDTO.getEstrategia());
            redirectAttributes.addFlashAttribute("mensaje", "Criterio de consenso actualizado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar");
        }
        return "redirect:/admin/ver-coleccion/" + id;
    }

    @PostMapping("/admin/colecciones/{idColeccion}/eliminar-hecho/{idHecho}")
    public String eliminarHecho(@PathVariable Long idHecho, @PathVariable Long idColeccion ,RedirectAttributes redirectAttributes) {
        hechoServicio.eliminarHechoDeColeccion(idColeccion, idHecho);

        redirectAttributes.addFlashAttribute("successMessage", "Hecho quitado de la colección exitosamente.");

        return "redirect:/admin/ver-coleccion/" + idColeccion;
    }
}