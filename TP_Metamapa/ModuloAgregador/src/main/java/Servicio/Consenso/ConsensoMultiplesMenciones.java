package Servicio.Consenso;

import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

@Component("Consenso multiples menciones")
public class ConsensoMultiplesMenciones extends Consenso{

    private ConsensoMultiplesMenciones(HechoRepositorio hechoRepositorio) {
        super(hechoRepositorio);
    }

    public Boolean tieneConsenso(Hecho hecho) {
        /*
        Set <Long> fuentes = repositorio.cantidadFuentesConHecho(hecho);
        return  fuentes.size() >=2 &&  repositorio.cantidadFuentesConTitulo(hecho.getTitulo(), fuentes);*/
        return false;
    }

}
