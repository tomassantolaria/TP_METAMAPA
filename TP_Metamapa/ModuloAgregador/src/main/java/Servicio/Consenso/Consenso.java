package Servicio.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;

public interface Consenso {
    public HechoRepositorio repositorio = new HechoRepositorio();
    public Boolean tieneConsenso(Hecho hecho);

    public default Integer cantidadFuentesConHecho(Hecho hecho) {
        return repositorio.cantidadFuentesConHecho(hecho.titulo);
    }
}
