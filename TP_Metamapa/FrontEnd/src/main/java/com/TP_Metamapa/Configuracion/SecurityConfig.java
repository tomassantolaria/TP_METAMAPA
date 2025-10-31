package com.TP_Metamapa.Configuracion;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor


public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER") // Spring Security adds ROLE_ automatically
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles("USER", "ADMINISTRADOR") // Roles defined here
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Recursos Estáticos y Páginas Públicas (permitAll)
                        .requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/navegar", "/estadisticas", "/ver-hecho/{id}", "/csv").permitAll()
                        .requestMatchers(HttpMethod.POST, "/registrar").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/registrarse").permitAll()
                        // 2. Acciones de Administrador (hasRole('ADMINISTRADOR'))
                        .requestMatchers("/admin/**").hasRole("ADMINISTRADOR") // Protege todo bajo /admin
                        // Si necesitas ser más específico para POSTs dentro de admin (opcional, ya cubierto por /admin/**):
                        // .requestMatchers(HttpMethod.POST, "/admin/cargar-csv", "/admin/proxy/crear", "/admin/crear-coleccion", etc.).hasRole("ADMINISTRADOR")

                        // 3. Acciones para Usuarios Autenticados (authenticated())

                        .requestMatchers(HttpMethod.GET, "/crear-hecho", "/solicitarEliminacion/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/crear-hecho", "/crearSolicitud").authenticated()

                        // 4. Regla General: Cualquier otra petición no definida arriba requiere autenticación
                        .anyRequest().authenticated()
                )
                // Configuración de Login y Logout (permitAll implícito aquí)
//                .formLogin(form -> form
//                       // .loginPage("/login") // Opcional: Define tu propia página de login
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/") // Opcional: A dónde ir después del logout
//                        .permitAll() );
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .sessionManagement(session -> session
                        // Usar STATELESS ya que la autenticación es vía JWT (token)
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );


        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Se asume la existencia de KeycloakRoleConverter.java para mapear los roles del realm
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return converter;
    }
}