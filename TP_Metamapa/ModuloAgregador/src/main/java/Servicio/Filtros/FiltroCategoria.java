package Servicio.Filtros;

import Modelos.Entidades.Hecho;
import org.springframework.stereotype.Service;

@Service
public class FiltroCategoria implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, String categoria) {

        return categoria == null || unHecho.getCategoria().getNombre().equalsIgnoreCase(categoria);
    }
    public FiltroCategoria(){}
}
