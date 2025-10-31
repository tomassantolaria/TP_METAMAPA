package com.TP_Metamapa.Controlador;

import com.TP_Metamapa.DTOS.ColeccionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.TP_Metamapa.DTOS.RegistroRequestDTO;
import com.TP_Metamapa.DTOS.RegistroResponseDTO;

import java.util.List;

@Controller
public class RegistroControlador {

    @Value("${backend.api.url}")
    private String backendApiUrl;

    @GetMapping("/registrarse")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("contribuyenteDTO", new RegistroRequestDTO());
        return "registrarse";
    }

    @PostMapping("/registrar")
    public String procesarRegistro(@ModelAttribute("contribuyenteDTO") RegistroRequestDTO dto, Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = backendApiUrl + "/registro/contribuyente";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RegistroRequestDTO> request = new HttpEntity<>(dto, headers);

            ResponseEntity<RegistroResponseDTO> response =
                     restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                             request,
                            new ParameterizedTypeReference<RegistroResponseDTO>() {}
                    );

            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("mensaje", "Registro exitoso. Inicia sesión para continuar.");
                return "redirect:/login"; // o redirigir a una vista de éxito
            } else {
                model.addAttribute("error", response.getBody().getMensaje());
                return "registrarse";
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error al conectar con el servidor: " + e.getMessage());
            return "registrarse";
        }
    }
}

