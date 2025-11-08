package com.keycloak.moduloauth.Controller;

import com.keycloak.moduloauth.DTOs.LoginDTO;
import com.keycloak.moduloauth.DTOs.KeycloakToken;
import com.keycloak.moduloauth.DTOs.RoleDTO;
import com.keycloak.moduloauth.DTOs.RegistroDTO;
import com.keycloak.moduloauth.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @GetMapping("/search")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(authService.findAllUsers());
    }


    @GetMapping("/search/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(authService.searchUserByUsername(username));
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<KeycloakToken> loginUser(@RequestBody LoginDTO loginDTO) {
        System.out.println("entre al controller login");
        KeycloakToken response = authService.loginUser(loginDTO);
        System.out.println("sale del controller");
        System.out.println("token: " + response.getAccess_token());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody RegistroDTO userDTO) throws URISyntaxException {
        String response = authService.createUser(userDTO);
        return ResponseEntity.created(new URI("/keycloak/user/create")).body(response);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody RegistroDTO userDTO){
        authService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        authService.deleteUser(userId);
        return ResponseEntity.noContent().build();
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
