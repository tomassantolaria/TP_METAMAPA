package Scheduler;


import Servicios.IHechoService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class FuenteDemoScheduler {
    private final IHechoService fuenteDemoService;

    public FuenteDemoScheduler(IHechoService fuenteDemoService) {
        this.fuenteDemoService = fuenteDemoService;
    }


    @Scheduled(fixedRate = 3600000) // cada 1HS
            public void actualizarHechos() {
            fuenteDemoService.actualizarHechos();
            }

}
// Este scheduler se ejecutará cada 1 hora para actualizar los hechos
