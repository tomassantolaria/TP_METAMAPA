package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Controlador", "Servicios", "Repositorios", "Modelos"})
public class Dinamica {
    public static void main(String[] args) {
        SpringApplication.run(Dinamica.class, args);
    }
}
