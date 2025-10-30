package Modelos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RegistroRequestDTO {
    public String keycloakUserId; // ID único de Keycloak
    public String usuario; // Usado como 'username' en Keycloak
    public String password;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;
    //public String email; // Keycloak requiere un email
}