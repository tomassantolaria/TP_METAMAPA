package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.HechoDTO;
import com.TP_Metamapa.DTOS.HechoDTOInput;
import com.TP_Metamapa.DTOS.HechoFormDTO;
import com.TP_Metamapa.DTOS.UserDataDTO;
import com.TP_Metamapa.Modelos.OrigenCarga;
import com.TP_Metamapa.Servicio.AuthService;
import com.TP_Metamapa.Servicio.CategoriaServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import com.TP_Metamapa.Servicio.HechoServicio;
import com.TP_Metamapa.Servicio.NavegacionServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class CrearHechoControlador {

    // GET → muestra el formulario
    final
    CategoriaServicio categoriaServicio;
    final
    NavegacionServicio navegacionServicio;
    final
    HechoServicio hechoServicio;
    final
    AuthService authService;

    public CrearHechoControlador(CategoriaServicio categoriaServicio, NavegacionServicio navegacionServicio, HechoServicio hechoServicio, AuthService authService) {
        this.categoriaServicio = categoriaServicio;
        this.navegacionServicio = navegacionServicio;
        this.hechoServicio = hechoServicio;
        this.authService = authService;
    }

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
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model // Para volver a mostrar el form si hay error
    ) {
        String imageUrl;
        try {
            // Validar que el usuario esté autenticado
            if (authentication == null || !authentication.isAuthenticated()) {
                model.addAttribute("errorMessage", "Debes iniciar sesión para crear un hecho.");
                return "redirect:/auth/login";
            }

            // Extraer username desde el Authentication
            String username = authentication.getName();

            // Recuperar el access token de la sesión
            String accessToken = (String) session.getAttribute("accessToken");

            if (accessToken == null) {
                model.addAttribute("errorMessage", "Tu sesión ha expirado. Por favor, inicia sesión nuevamente.");
                return "redirect:/auth/login";
            }

            // Obtener datos completos del usuario desde el módulo de auth
            UserDataDTO userData = authService.getUserData(username, accessToken);

            if (userData == null) {
                model.addAttribute("errorMessage", "No se pudo obtener la información del usuario.");
                List<String> categorias = categoriaServicio.getCategoriasUnicas();
                model.addAttribute("categorias", categorias);
                return "crearHecho";
            }

            String firstName = userData.getFirstName();
            String lastName = userData.getLastName();
            LocalDate birthdate = userData.getBirthdate();

            System.out.println("auth: " + authentication);
            System.out.println("usuario: " + username);
            System.out.println("nombre: " + firstName);
            System.out.println("apellido: " + lastName);
            System.out.println("fecha nacimiento: " + birthdate);

            // Validar que los datos del usuario estén completos
            if (username == null || firstName == null || lastName == null) {
                model.addAttribute("errorMessage", "Los datos del usuario son incompletos. Contacta al administrador.");
                List<String> categorias = categoriaServicio.getCategoriasUnicas();
                model.addAttribute("categorias", categorias);
                return "crearHecho";
            }

            // 1. Guardar la imagen localmente (si existe) y obtener su URL relativa
            imageUrl = hechoServicio.guardarMultimediaLocalmente(multimediaFile);

            // 2. Crear el DTO que se enviará al backend
            HechoDTOInput hechoParaBackend = new HechoDTOInput();
            hechoParaBackend.setTitulo(hechoFormData.getTitulo());
            hechoParaBackend.setDescripcion(hechoFormData.getDescripcion());
            hechoParaBackend.setContenido(hechoFormData.getContenidoAdicional());
            hechoParaBackend.setContenido_multimedia(imageUrl);
            hechoParaBackend.setFechaAcontecimiento(hechoFormData.getFechaAcontecimiento());
            hechoParaBackend.setLocalidad(hechoFormData.getLocalidad());
            hechoParaBackend.setProvincia(hechoFormData.getProvincia());
            hechoParaBackend.setPais(hechoFormData.getPais());
            hechoParaBackend.setLatitud(hechoFormData.getLatitud());
            hechoParaBackend.setLongitud(hechoFormData.getLongitud());
            hechoParaBackend.setUsuario(username);
            hechoParaBackend.setNombre(firstName);
            hechoParaBackend.setApellido(lastName);
            hechoParaBackend.setFechaNacimiento(birthdate);
            hechoParaBackend.setAnonimo(hechoFormData.isAnonimo());

            if ("Otra".equals(hechoFormData.getCategoria()) && hechoFormData.getCustomCategoria() != null && !hechoFormData.getCustomCategoria().isEmpty()) {
                hechoParaBackend.setCategoria(hechoFormData.getCustomCategoria());
            } else {
                hechoParaBackend.setCategoria(hechoFormData.getCategoria());
            }
            System.out.println("hecho que va para el back: " + hechoParaBackend);
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