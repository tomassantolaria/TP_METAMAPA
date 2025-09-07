package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Scheduler","Controlador", "Servicios", "Repositorios", "Modelos"})
public class Estatica {
    public static void main(String[] args) {
        SpringApplication.run(Estatica.class, args);
    }
}
