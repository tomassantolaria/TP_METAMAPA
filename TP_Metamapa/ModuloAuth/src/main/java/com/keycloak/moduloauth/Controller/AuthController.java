package com.keycloak.moduloauth.Controller;

import com.keycloak.moduloauth.DTOs.LoginDTO;
import com.keycloak.moduloauth.DTOs.KeycloakToken;
import com.keycloak.moduloauth.DTOs.RoleDTO;
import com.keycloak.moduloauth.DTOs.RegistroDTO;
import com.keycloak.moduloauth.Services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/search")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(authService.findAllUsers());
    }


    @GetMapping("/search/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(authService.searchUserByUsername(username));
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        System.out.println("entre al controller login");
        try {
            KeycloakToken response = authService.loginUser(loginDTO);
            System.out.println("sale del controller");
            System.out.println("token: " + response.getAccess_token());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.err.println("Error en login: " + e.getMessage());

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "authentication_failed");
            errorResponse.put("message", e.getMessage());

            //401 si se intenta iniciar sesion con datos incorrectos
            if (e.getMessage().contains("Usuario o contraseña incorrectos")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }

            //500 para otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody RegistroDTO userDTO) throws URISyntaxException {
        String response = authService.createUser(userDTO);
        return ResponseEntity.created(new URI("/keycloak/user/create")).body(response);
    }

    @GetMapping("/role")
    public ResponseEntity<RoleDTO> getRole(Authentication authentication){
        System.out.println(authentication.getPrincipal());
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(r -> r.startsWith("ROLE_"))
                .toList();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoles(roles);
        return ResponseEntity.ok(roleDTO);
    }
}
