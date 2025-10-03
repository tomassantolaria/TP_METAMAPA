package Scheduler;

import Servicio.AgregadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AgregadorScheduler {

    @Autowired
    private final AgregadorServicio agregadorService;

    public AgregadorScheduler(AgregadorServicio agregadorService) {
        this.agregadorService = agregadorService;
    }


    @Scheduled(fixedRate = 120000) // TODO :cambiar a 1 hs
    public void actualizarHechos() {
        agregadorService.actualizarHechos();
    }
}
