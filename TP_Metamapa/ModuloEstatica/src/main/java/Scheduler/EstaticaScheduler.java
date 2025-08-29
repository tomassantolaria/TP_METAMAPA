package Scheduler;

import Servicio.FuenteEstatica;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EstaticaScheduler {

    private final FuenteEstatica fuenteEstatica;

    public EstaticaScheduler(FuenteEstatica fuenteEstatica) {
        this.fuenteEstatica = fuenteEstatica;
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void actualizarHechos() {
        try {
            fuenteEstatica.cargarHechos();
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }
}
