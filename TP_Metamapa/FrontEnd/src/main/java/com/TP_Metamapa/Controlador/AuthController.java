package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.LoginDTO;
import com.TP_Metamapa.DTOS.RegisterDTO;
import com.TP_Metamapa.Servicio.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        model.addAttribute("credenciales", new LoginDTO());

        if (error != null) {
            model.addAttribute("errorMessage", "Usuario o contraseña incorrectos. Por favor, inténtalo nuevamente.");
        }

        if (logout != null) {
            model.addAttribute("successMessage", "Has cerrado sesión exitosamente.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") RegisterDTO registerDTO) {
        String response = authService.register(registerDTO);
        System.out.println("Respuesta del registro: " + response);
        return "redirect:/auth/login";
    }

}
