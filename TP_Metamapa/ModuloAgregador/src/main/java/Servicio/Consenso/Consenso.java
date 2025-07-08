package Servicio.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import java.util.*;
public interface Consenso {
    public HechoRepositorio repositorio = new HechoRepositorio();
    public Boolean tieneConsenso(Hecho hecho);

    public default Set <Integer> cantidadFuentesConHecho(Hecho hecho) {
        return repositorio.cantidadFuentesConHecho(hecho);
    }
}
