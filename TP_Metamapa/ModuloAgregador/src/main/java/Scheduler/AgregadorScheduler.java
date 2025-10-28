package Scheduler;

import Servicio.AgregadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AgregadorScheduler {

    @Autowired
    AgregadorServicio agregadorService;

    @Transactional
    @Scheduled(fixedRate = 36000000)
    public void actualizarHechos() {
        agregadorService.actualizarHechos();
    }
}
