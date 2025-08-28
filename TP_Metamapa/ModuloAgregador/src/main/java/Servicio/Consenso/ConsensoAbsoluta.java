package Servicio.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("Consenso absoluto")
public class ConsensoAbsoluta extends Consenso {

    public ConsensoAbsoluta(HechoRepositorio repositorio) {
        super(repositorio);
    }

    @Override
    public Boolean tieneConsenso(Hecho hecho) {
        return cantidadFuentesConHecho(hecho).size() >= 4;
    }
}

