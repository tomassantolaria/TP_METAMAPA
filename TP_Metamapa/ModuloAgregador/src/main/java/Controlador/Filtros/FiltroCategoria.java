package Controlador.Filtros;

import Controlador.Modelos.Entidades.Categoria;
import Controlador.Modelos.Entidades.Hecho;
import Controlador.Modelos.Entidades.Criterios;

public class FiltroCategoria implements Filtro{
    @Override
    public boolean cumple(Hecho unHecho, Criterios criterios){
        Categoria categoria = criterios.getCategoria();
        return categoria == null || unHecho.getCategoria() == categoria;
    }
    public FiltroCategoria(){}
}
