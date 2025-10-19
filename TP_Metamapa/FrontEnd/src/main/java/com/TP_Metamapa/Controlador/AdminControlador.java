package com.TP_Metamapa.Controlador;


import com.TP_Metamapa.DTOS.ColeccionDTO;
import com.TP_Metamapa.DTOS.FuentesDTO;
import com.TP_Metamapa.DTOS.SolicitudDTO;
import com.TP_Metamapa.Modelos.TipoFuente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminControlador {

    // @Autowired
    // private ColeccionServicio coleccionServicio;
    // @Autowired
    // private SolicitudServicio solicitudServicio;

    @GetMapping("/admin")
    public String panelDeAdmin(
            @RequestParam(name = "tab", defaultValue = "collections") String activeTab,
            Model model
    ) {
        List<ColeccionDTO> coleccionesDePrueba = new ArrayList<>();
        List<SolicitudDTO> solicitudes = new ArrayList<>();
        List<FuentesDTO> fuentes = new ArrayList<>();
        TipoFuente[] tipoFuentes = TipoFuente.values();
        switch (activeTab) {
            case "collections":
                // List<ColeccionDTO> colecciones = coleccionServicio.getColecciones();
                // model.addAttribute("colecciones", colecciones);

                ColeccionDTO c1 = new ColeccionDTO(1L, "Avistamientos de Fauna", "Hechos relacionados con animales.", null, null,"MAYORIA_SIMPLE",null);
                ColeccionDTO c2 = new ColeccionDTO(2L, "Sucesos Históricos", "Eventos importantes de la historia.", null, null,"MAYORIA_SIMPLE",null);
                ColeccionDTO c3 =  new ColeccionDTO(3L, "Incendios Forestales", "Reportes de incendios en zonas naturales.", null, null,null,null);
                coleccionesDePrueba.add(c1);
                coleccionesDePrueba.add(c2);
                coleccionesDePrueba.add(c3);

                break;
            case "requests":
                // List<SolicitudDTO> solicitudes = solicitudServicio.obtenerPendientes();
                SolicitudDTO s1 = new SolicitudDTO(1L, "Incluir colección de Avistamientos de Fauna", 1L, LocalDateTime.now());
                SolicitudDTO s2 = new SolicitudDTO(2L, "Modificar colección de Sucesos Históricos", 1L, LocalDateTime.now());
                solicitudes.add(s1);
                solicitudes.add(s2);
                break;
            case "static":
                // No se necesitan datos adicionales para esta pestaña
                break;
            case "proxy":
                // Lista<FuentesDTO> fuentes = fuenteProxyServicio.obtenerTodas();
                fuentes.add(new FuentesDTO("http://ejemplo.com/api", TipoFuente.METAMAPA));
                break;
            default:
                break;
        }
        model.addAttribute("fuentesProxy", fuentes);
        model.addAttribute("colecciones", coleccionesDePrueba);
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

        // Verificación 2: que sea un archivo CSV (opcional pero recomendado)
        if (!"text/csv".equals(file.getContentType())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: El archivo debe ser de formato CSV.");
            return "redirect:/admin?tab=static";
        }

        try {
            // Aquí iría la lógica para leer y procesar el archivo
            // Por ejemplo: myCsvProcessingService.procesar(file.getInputStream());

            System.out.println("Archivo recibido: " + file.getOriginalFilename());
            System.out.println("Tamaño: " + file.getSize() + " bytes");

            // Si todo sale bien, mandamos un mensaje de éxito
            redirectAttributes.addFlashAttribute("successMessage", "¡Archivo '" + file.getOriginalFilename() + "' cargado exitosamente!");

        } catch (Exception e) {
            // Si algo falla durante el procesamiento, mandamos un mensaje de error
            redirectAttributes.addFlashAttribute("errorMessage", "Error al procesar el archivo: " + e.getMessage());
        }

        // Redirigimos de vuelta a la misma pestaña del admin
        return "redirect:/admin?tab=static";
    }


    @PostMapping("/admin/proxy/crear")
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
            // Aquí llamarías a tu servicio para guardar la nueva fuente
            // fuenteProxyService.crear(url, tipoFuente);

            System.out.println("Nueva fuente recibida: URL=" + url + ", Tipo=" + tipoFuente);

            redirectAttributes.addFlashAttribute("successMessage", "¡Fuente Proxy '" + url + "' creada con éxito!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear la fuente: " + e.getMessage());
        }

        return "redirect:/admin?tab=proxy";
    }
}
