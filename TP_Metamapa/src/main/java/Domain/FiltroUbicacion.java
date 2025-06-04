package Domain;

public class FiltroUbicacion implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        Ubicacion criterio = criterios.getUbicacion();
        return criterio == null || unHecho.getUbicacion() == criterio;
    }
}