package Servicio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repositorio.EstadisticasRepositorio;


@Service
class EstadisticasServicio{

@Autowired
private final EstadisticasRepositorio estadisticasRepositorio;


public String provinciaConMasHechos(Long idColeccion){
    String resultado = estadisticasRepositorio.getProvinciaConMasHechos(idColeccion, PageRequest.of(0, 1));
    return resultado.isEmpty() ? "Todavía no hay hechos cargados." : resultado.get(0);
}

public String categoriaConMasHechos(){
    String resultado = estadisticasRepositorio.getCategoriaConMashechos(PageRequest.of(0,1));
    return resultado.isEmpty() ? "Todavía no hay hechos cargados." : resultado.get(0);
}

public String provinciaConMasHechosDeCategoria(String categoria){
    String resultado = estadisticasRepositorio.getProvinciaConMasHechosDeCategoria(categoria, PageRequest.of(0,1));
    return resultado.isEmpty() ? "Todavía no hay hechos cargados." : resultado.get(0);
}

public Integer obtenerHoraConMasHechos(String categoria){
    Integer resultado = estadisticasRepositorio.getHoraConMasHechos(categoria, PageRequest.of(0,1));  
    return resultado.isEmpty() ? 0 : resultado.get(0);
}

public Long cantidadSolicitudesSpam(){
    Long resultado = estadisticasRepositorio.getCantidadSolicitudesSpam();
    return resultado.isEmpty() ? 0;
}

}