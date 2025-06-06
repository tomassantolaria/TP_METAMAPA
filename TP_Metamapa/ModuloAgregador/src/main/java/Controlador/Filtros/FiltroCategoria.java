package Controlador.Filtros;

import Controlador.Categoria;
import Controlador.Hecho;
import Controlador.Criterios;

public class FiltroCategoria implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        Categoria categoria = criterios.getCategoria();
        return categoria == null || unHecho.getCategoria() == categoria;
    }
    public FiltroCategoria(){}
}
