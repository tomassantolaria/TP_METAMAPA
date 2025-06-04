package Domain;

public class FiltroOrigenCarga implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        OrigenCarga criterio = criterios.getOrigenCarga();
        return criterio == null || unHecho.getOrigen() == criterio;
    }
}
