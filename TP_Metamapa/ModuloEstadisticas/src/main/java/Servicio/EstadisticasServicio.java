package Servicio;

import Modelos.DTOs.UltimasEstadisticasDTO;
import Repositorio.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EstadisticasServicio{

    @Autowired
    ProvinciaRepositorio provinciaRepositorio;
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    @Autowired
    HechoRepositorio hechoRepositorio;
    @Autowired
    UltimasEstadisticasRepositorio ultimasEstadisticasRepositorio;
    @Autowired
    SolicitudRepositorio solicitudRepositorio;

    public String provinciaConMasHechos(Long idColeccion){
        List<String> resultado = provinciaRepositorio.getProvinciaConMasHechos(idColeccion, PageRequest.of(0, 1));
        return resultado.isEmpty() ? "Todavía no hay hechos cargados." : resultado.get(0);
    }

    public String categoriaConMasHechos(){
        List<String> resultado = categoriaRepositorio.getCategoriaConMasHechos(PageRequest.of(0,1));
        return resultado.isEmpty() ? "Todavía no hay hechos cargados." : resultado.get(0);
    }

    public String provinciaConMasHechosDeCategoria(String categoria){
        List<String> resultado = provinciaRepositorio.getProvinciaConMasHechosDeCategoria(categoria, PageRequest.of(0,1));
        return resultado.isEmpty() ? "Todavía no hay hechos cargados." : resultado.get(0);
    }

    public Integer obtenerHoraConMasHechos(String categoria){
        List<Integer> resultado = hechoRepositorio.getHoraConMasHechos(categoria, PageRequest.of(0,1));
        return resultado.isEmpty() ? 0 : resultado.get(0);
    }

    public Long cantidadSolicitudesSpam(){
        Long resultado = solicitudRepositorio.getCantidadSolicitudesSpam();
        return resultado == null ? 0 : resultado;
    }

    //cada una hora
    @Scheduled(fixedRate = 3600000)
    public void recalcularEstadisticas() {
            if(!ultimasEstadisticasRepositorio.isEmpty()){
                ultimasEstadisticasRepositorio.vaciarCache();}
            UltimasEstadisticasDTO dto = new UltimasEstadisticasDTO();
            Map<Long, String> provinciaPorColeccion = new HashMap<>();
            for(Long idColeccion : hechoRepositorio.getColeccionId()){
                provinciaPorColeccion.put(idColeccion,this.provinciaConMasHechos(idColeccion));
            }
            dto.setProvinciaConMasHechosPorColeccion(provinciaPorColeccion);

            dto.setCategoriaConMasHechos(this.categoriaConMasHechos());

            Map<String, String> provinciaPorCategoria = new HashMap<>();
            List<String> categorias = categoriaRepositorio.getCategorias();
            for (String categoria : categorias) {
                provinciaPorCategoria.put(categoria, this.provinciaConMasHechosDeCategoria(categoria));
            }
            dto.setProvinciaPorCategoria(provinciaPorCategoria);

            Map<String, Integer> horaPorCategoria = new HashMap<>();
            for (String categoria : categorias) {
                horaPorCategoria.put(categoria, this.obtenerHoraConMasHechos(categoria));
            }
            dto.setHoraPorCategoria(horaPorCategoria);

            dto.setCantidadSolicitudesSpam(this.cantidadSolicitudesSpam());

            ultimasEstadisticasRepositorio.save(dto);
        }

        public UltimasEstadisticasDTO obtenerEstadisticas() {
            return ultimasEstadisticasRepositorio.getCache();
        }

        public String exportarCSV() {
            UltimasEstadisticasDTO dto = ultimasEstadisticasRepositorio.getCache();
            if (dto == null) return "";

            StringBuilder csv = new StringBuilder();
            csv.append("Provincia con más hechos por colección:\nIdColección,Provincia\n");
            dto.getProvinciaConMasHechosPorColeccion().forEach((col, prov) -> {
                csv.append(col).append(",").append(prov).append("\n");
            });
            csv.append("Categoría con más hechos: ").append(dto.getCategoriaConMasHechos()).append("\n");

            csv.append("Provincia con más hechos por categoría:\nCategoria,Provincia\n");
            dto.getProvinciaPorCategoria().forEach((cat, prov) -> {
                csv.append(cat).append(",").append(prov).append("\n");
            });

            csv.append("Hora por categoría:\nCategoria,Hora\n");
            dto.getHoraPorCategoria().forEach((cat, hora) -> {
                csv.append(cat).append(",").append(hora).append("\n");
            });

            csv.append("Cantidad de solicitudes spam: ").append(dto.getCantidadSolicitudesSpam()).append("\n");

            return csv.toString();
        }





}