package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"Configuracion","Controlador", "Servicios", "Modelos"})
@EntityScan(basePackages = "Modelos")
public class ModuloRegistro {
    public static void main(String[] args) {
        SpringApplication.run(ModuloRegistro.class, args);
    }
}