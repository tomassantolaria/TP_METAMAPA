package Servicios;

import Modelos.DTOS.RegistroRequestDTO;
import Modelos.DTOS.RegistroResponseDTO;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class KeyCloakUserService {

    @Autowired
    Keycloak keycloakAdminClient;
    @Autowired
    RestTemplate restTemplate;

    @Value("${keycloak.realm-name}")
    private String realmName;

    // URL interna del Modulo Dinamica
    //private static final String DINAMICA_BASE_URL = "http://localhost:8081/dinamica";

    //public KeyCloakUserService(Keycloak keycloakAdminClient, RestTemplate restTemplate) {
        //this.keycloakAdminClient = keycloakAdminClient;
      //  this.restTemplate = restTemplate;
    //}

    // Método para sincronizar la información local después del registro en Keycloak



    public RegistroResponseDTO registrarContribuyente(RegistroRequestDTO registroDTO) {
        // 1. Crear Credenciales (Contraseña)
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(registroDTO.getPassword());
        credential.setTemporary(false);

        // 2. Crear Representación del Usuario
        UserRepresentation user = new UserRepresentation();
        user.setUsername(registroDTO.getUsuario());
        user.setFirstName(registroDTO.getNombre());
        user.setLastName(registroDTO.getApellido());
        //user.setEmail(registroDTO.getEmail());
        // Generar un email ficticio basado en el nombre de usuario
       String generatedEmail = registroDTO.getUsuario().toLowerCase() + "@metamapa.local";
        user.setEmail(generatedEmail);
        // o si el DTO aún lo recibe, usar: user.setEmail(registroDTO.getEmail());
        user.setEnabled(true);
        user.setCredentials(Collections.singletonList(credential));

        // 3. Crear Usuario en Keycloak
        try (Response response = keycloakAdminClient.realm(realmName).users().create(user)) {

            if (response.getStatus() == 201) {
                String path = response.getLocation().getPath();
                String keycloakUserId = path.substring(path.lastIndexOf('/') + 1);

                // --- POST-REGISTRO: Asignar Rol y Persistir en BD Local ---
                try {
                    // 1. Añadir el ID de Keycloak al DTO
                    registroDTO.setKeycloakUserId(keycloakUserId);

                    // 2. Llamada al endpoint interno de Dinamica (puerto 8081)
                    String urlDinamica = "http://localhost:8082/registrarse/";

                    restTemplate.postForEntity(urlDinamica, registroDTO, String.class);

                } catch (Exception e) {
                    System.err.println("Error al sincronizar datos locales en Modulo Dinamica: " + e.getMessage());
                    // NOTA: El usuario existe en Keycloak pero no en Dinamica. Se debe manejar como error crítico.
                }
                // 4. Asignar Rol
                String roleName = "CONTRIBUYENTE"; // Asume que este rol existe en Keycloak
                var realmResource = keycloakAdminClient.realm(realmName);
                var role = realmResource.roles().get(roleName).toRepresentation();
                realmResource.users().get(keycloakUserId).roles().realmLevel().add(Collections.singletonList(role));

                // 5. Llamar a ModuloDinamica para guardar datos no sensibles (requiere RestTemplate, omitido aquí por simplicidad)
                // En un escenario real, aquí se usaría un RestTemplate para llamar a un endpoint interno del ModuloDinamica
                // para que persista los datos del contribuyente (nombre, apellido, fecha_nacimiento, y el keycloakUserId/username).

                return new RegistroResponseDTO(keycloakUserId, "Usuario Keycloak y Contribuyente registrado con éxito.", 201);
            } else {
                return new RegistroResponseDTO(null, "Error al crear usuario en Keycloak. Verifique logs y Keycloak Admin Client settings.", response.getStatus());
            }

        } catch (Exception e) {
            System.err.println("Error comunicándose con Keycloak: " + e.getMessage());
            e.printStackTrace();
            return new RegistroResponseDTO(null, "Error interno del servidor.", 500);
        }
    }
}