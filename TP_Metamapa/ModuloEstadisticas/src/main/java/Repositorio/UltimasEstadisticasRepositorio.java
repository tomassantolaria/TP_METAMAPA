package Repositorio;


import Modelos.UltimasEstadisticasDTO;
import org.springframework.stereotype.Repository;

@Repository
public class UltimasEstadisticasRepositorio {
    private volatile UltimasEstadisticasDTO cache;

    public void save(UltimasEstadisticasDTO estadisticas){ this.cache = estadisticas; }
    public UltimasEstadisticasDTO getCache(){ return this.cache; }
    public void vaciarCache(){ this.cache = null; }
    public boolean isEmpty(){ return this.cache == null; }

}
