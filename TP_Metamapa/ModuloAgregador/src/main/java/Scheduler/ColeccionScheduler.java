package Scheduler;



import Servicio.ColeccionServicio;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ColeccionScheduler {

    private final ColeccionServicio coleccionServicio;

    public ColeccionScheduler(ColeccionServicio coleccionServicio) {
        this.coleccionServicio = coleccionServicio;
    }


    @Scheduled(cron = "0 0 3 * * ?") //  en horarios d baja carga en el sistema.
    public void actualizarHechos() {
        coleccionServicio.actalizarHechosConsensuados();
    }
}
