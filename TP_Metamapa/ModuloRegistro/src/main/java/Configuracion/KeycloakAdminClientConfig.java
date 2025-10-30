package Configuracion;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakAdminClientConfig {

    @Value("${keycloak.registro-server-url}")
    private String registroServerUrl;

    @Value("${keycloak.realm-name}")
    private String realmName;

    @Value("${keycloak.admin-username}")
    private String adminUsername;

    @Value("${keycloak.admin-password}")
    private String adminPassword;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Bean
    public Keycloak keycloakAdminClient() {
        return KeycloakBuilder.builder()
                .serverUrl(registroServerUrl)
                .realm(realmName)
                .username(adminUsername)
                .password(adminPassword)
                .clientId(clientId)
                .grantType("password")
                .build();
    }
}