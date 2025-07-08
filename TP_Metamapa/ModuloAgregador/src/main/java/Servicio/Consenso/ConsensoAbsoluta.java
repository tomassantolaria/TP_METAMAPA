package Servicio.Consenso;
import Modelos.Entidades.Hecho;

public class ConsensoAbsoluta implements Consenso {
    private static ConsensoAbsoluta instancia;

    private ConsensoAbsoluta() {
    }

    public static ConsensoAbsoluta getInstance() {
        if (instancia == null) {
            instancia = new ConsensoAbsoluta();
        }
        return instancia;
    }

    public Boolean tieneConsenso(Hecho hecho) {
        return cantidadFuentesConHecho(hecho) >= 3;
    }

}
