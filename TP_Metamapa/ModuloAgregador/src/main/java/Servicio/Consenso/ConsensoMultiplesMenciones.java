package Servicio.Consenso;

import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import java.util.*;

public class ConsensoMultiplesMenciones implements Consenso{
    private static ConsensoMultiplesMenciones instancia;
    private HechoRepositorio repositorio = new HechoRepositorio();
    private ConsensoMultiplesMenciones() {

    }

    public static ConsensoMultiplesMenciones getInstance() {
        if (instancia == null) {
            instancia = new ConsensoMultiplesMenciones();
        }
        return instancia;
    }

    public Boolean tieneConsenso(Hecho hecho) {
        Set <Integer> fuentes = repositorio.cantidadFuentesConHecho(hecho);
        return  fuentes.size() >=2 &&  repositorio.cantidadFuentesConTitulo(hecho.getTitulo(), fuentes);
    }

}
