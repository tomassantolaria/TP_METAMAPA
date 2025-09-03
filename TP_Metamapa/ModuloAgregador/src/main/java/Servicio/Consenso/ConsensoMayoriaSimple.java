package Servicio.Consenso;

import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("Consenso mayoria simple")
public class ConsensoMayoriaSimple extends Consenso {

    private ConsensoMayoriaSimple(HechoRepositorio repositorio) {
        super(repositorio);
    }

    public Boolean tieneConsenso(Hecho hecho) {

        Long cantidadFuentes = repositorio.cantidadFuentes();
        return (cantidadFuentesConHecho(hecho)) >= cantidadFuentes / 2;
    }

}
