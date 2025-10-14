package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Configuracion", "Scheduler","Controlador", "servicios", "Repositorios", "Modelos"})
@EnableJpaRepositories(basePackages = "Repositorios")
@EntityScan(basePackages = "Modelos")
public class Proxy {
    public static void main(String[] args) {
        SpringApplication.run(Proxy.class, args);
    }
}