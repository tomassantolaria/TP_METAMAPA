package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Scheduler","Controlador", "Servicio", "Repositorio", "Modelos"})
@EnableJpaRepositories(basePackages = "Repositorio")
@EntityScan(basePackages = "Modelos")
public class Estatica {
    public static void main(String[] args) {
        SpringApplication.run(Estatica.class, args);
    }
}
