package com.keycloak.moduloauth.Utils;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloackProvider {

        @Value("${url.keycloak}")
        private String URL_SERVER;
        @Value("${jwt.auth.converter.realm-name}")
        private String REALM_NAME;
        @Value("${jwt.auth.converter.realm-master}")
        private String REALM_MASTER;
        @Value("${jwt.auth.converter.admin-cli}")
        private String ADMIN_CLI;
        @Value("${jwt.auth.converter.user-console}")
        private String USER_CONSOLE;
        @Value("${jwt.auth.converter.password-console}")
        private String PASSWORD_CONSOLE;
        @Value("${jwt.auth.converter.client-secret}")
        private String CLIENT_SECRET;
        

        public  RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(URL_SERVER)
                .realm(REALM_MASTER)
                .clientId(ADMIN_CLI)
                .username(USER_CONSOLE)
                .password(PASSWORD_CONSOLE)
                .clientSecret(CLIENT_SECRET)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10)
                        .build())
                .build();

        return keycloak.realm(REALM_NAME);
    }

    public UsersResource getUserResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }

}
