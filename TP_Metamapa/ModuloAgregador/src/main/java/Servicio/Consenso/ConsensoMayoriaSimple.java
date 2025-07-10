package Servicio.Consenso;

import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("Consenso mayoria simple")
public class ConsensoMayoriaSimple extends Consenso {

    public Integer cantidadFuentes = 4; //ver

    private ConsensoMayoriaSimple(HechoRepositorio repositorio) {
        super(repositorio);
    }

    public Boolean tieneConsenso(Hecho hecho) {

        return cantidadFuentesConHecho(hecho).size() >= cantidadFuentes / 2;
    }

}
