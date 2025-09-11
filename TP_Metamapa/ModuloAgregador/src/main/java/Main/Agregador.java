package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"Configuracion", "Scheduler","Controlador", "Servicio", "Repositorio", "Modelos"})
@EnableJpaRepositories(basePackages = "Repositorio")
@EntityScan(basePackages = "Modelos")
@EnableScheduling
public class Agregador {
    public static void main(String[] args) {
        SpringApplication.run(Agregador.class, args);
    }
}
