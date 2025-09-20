package Scheduler;

import Servicio.FuenteEstatica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EstaticaScheduler {

    private final FuenteEstatica fuenteEstatica;
    @Autowired
    public EstaticaScheduler(FuenteEstatica fuenteEstatica) {
        this.fuenteEstatica = fuenteEstatica;
    }

    //@Scheduled(cron = "0 0 4 * * ?") //
    @Scheduled(initialDelay = 0, fixedRate = 60000)
    public void actualizarHechos() {
        System.out.println("Actualizando hechos...");
        try {
            fuenteEstatica.cargarHechos();
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
}
