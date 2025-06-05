package Scheduler;


import Servicios.IHechoService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class FuenteDemoScheduler {
    private final IHechoService hechoService;

    public FuenteDemoScheduler(IHechoService hechoService) {
        this.hechoService = hechoService;
    }


    @Scheduled(fixedRate = 3600000) // cada 1HS
            public void actualizarHechos() {
            hechoService.actualizarHechos();
            }

}
// Este scheduler se ejecutará cada 1 hora para actualizar los hechos
