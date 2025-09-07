package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Configuracion", "Scheduler","Controlador", "Servicios", "Repositorios", "Modelos"})
public class ModuloPublico {
    public static void main(String[] args) {
        SpringApplication.run(ModuloPublico.class, args);
    }
}
