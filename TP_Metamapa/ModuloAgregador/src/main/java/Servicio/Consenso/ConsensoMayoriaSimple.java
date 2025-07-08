package Servicio.Consenso;

import Modelos.Entidades.Hecho;

public class ConsensoMayoriaSimple implements Consenso {
    public Integer cantidadFuentes = 4; //ver
    private static ConsensoMayoriaSimple instancia;

    private ConsensoMayoriaSimple() {

    }

    public static ConsensoMayoriaSimple getInstance() {
        if (instancia == null) {
            instancia = new ConsensoMayoriaSimple();
        }
        return instancia;
    }

    public Boolean tieneConsenso(Hecho hecho) {

        return cantidadFuentesConHecho(hecho) >= cantidadFuentes / 2;
    }

}
