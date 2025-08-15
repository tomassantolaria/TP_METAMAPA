package Servicio.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import java.util.*;

public abstract class Consenso {

    public final HechoRepositorio repositorio;

    protected Consenso(HechoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public abstract Boolean tieneConsenso(Hecho hecho);

    public Set <UUID> cantidadFuentesConHecho(Hecho hecho) {
        return repositorio.cantidadFuentesConHecho(hecho);
    }
}
