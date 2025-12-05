package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Configuracion","Controlador", "Servicio", "Repositorio", "Modelos", "RateLimit"})
@EnableJpaRepositories(basePackages = "Repositorio")
@EntityScan(basePackages = "Modelos")
public class ModuloPublico {
    public static void main(String[] args) {
        SpringApplication.run(ModuloPublico.class, args);
    }
}
