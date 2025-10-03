package Scheduler;


import Servicios.FuenteDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class FuenteDemoScheduler {
    
    @Autowired
    FuenteDemoService fuenteDemoService;

    @Scheduled(fixedRate = 3600000) // cada 1HS
    public void actualizarHechos() {
        fuenteDemoService.actualizarHechos();
    }

}
// Este scheduler se ejecutará cada 1 hora para actualizar los hechos