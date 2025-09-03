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
        Boolean noHayConIgualTituloYDiferentesAtributos = repositorio.cantidadDeFuentesConMismoTituloDiferentesAtributos(hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getFecha(), hecho.getUbicacion(), hecho.getContribuyente(), hecho.getContenido()) == 0 ;

        return (cantidadFuentesConHecho(hecho) >= 2) && (noHayConIgualTituloYDiferentesAtributos);
    }

}
