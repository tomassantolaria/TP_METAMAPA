package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Controller", "Servicios", "Modelos"})
public class Filtrador {
    public static void main(String[] args) {
        SpringApplication.run(Filtrador.class, args);
    }
}
