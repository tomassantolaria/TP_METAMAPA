package Servicio.Filtros;

import Modelos.Entidades.CriteriosDePertenencia;
import Modelos.Entidades.Hecho;
import org.springframework.stereotype.Service;

@Service
public class FiltroCategoria implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, CriteriosDePertenencia criterio) {

        return criterio.getCategoria() == null || unHecho.getCategoria().getNombre().equalsIgnoreCase(criterio.getCategoria().getNombre());
    }
    public FiltroCategoria(){}
}
