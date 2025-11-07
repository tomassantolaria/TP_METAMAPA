package com.keycloak.moduloauth.Controller;

import com.keycloak.moduloauth.DTOs.LoginDTO;
import com.keycloak.moduloauth.DTOs.KeycloakToken;
import com.keycloak.moduloauth.DTOs.UsuarioDTO;
import com.keycloak.moduloauth.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/auth")
//@RequestMapping("/keycloak/user")
// @PreAuthorize("hasRole('admin_client_role')")
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
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        System.out.println("entre al controller login");
        String response = authService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UsuarioDTO userDTO) throws URISyntaxException {
        String response = authService.createUser(userDTO);
        return ResponseEntity.created(new URI("/keycloak/user/create")).body(response);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UsuarioDTO userDTO){
        authService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        authService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
