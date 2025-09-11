package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Controlador", "Servicios", "Repositorio", "Modelos"})
public class Normalizador {
    public static void main(String[] args) {
        SpringApplication.run(Normalizador.class, args);
    }
}