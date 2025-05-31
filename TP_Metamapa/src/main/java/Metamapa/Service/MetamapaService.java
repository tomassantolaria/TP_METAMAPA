package Metamapa.Service;

import Dinamica.Controller.HechoDTO;
import Domain.*;
import Metamapa.Repository.MetamapaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import Metamapa.Controller.CriterioDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MetamapaService {

    private final MetamapaRepository metamapaRepository;

    public MetamapaService(MetamapaRepository metamapaRepository) {

        this.metamapaRepository = metamapaRepository;
    }

    public List<HechoDTO> filtrarHechos(@RequestBody CriterioDTO criterioDTO) {
        Busqueda criterios_busqueda = this.crearBusqueda(criterioDTO);
        // mandar a organizador y hacer busqueda
    }

    private Busqueda crearBusqueda(CriterioDTO criterios) {
        Busqueda busqueda = new Busqueda();
        busqueda.setTitulo(criterios.getTitulo());
        busqueda.setDescripcion(criterios.getDescripcion());
        Categoria categoria = new Categoria(criterios.getCategoria());
        busqueda.setCategoria(categoria);
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(criterios.getContenido_multimedia());
        Contenido contenido = new Contenido(criterios.getContenido_texto(),contenido_multimedia);
        busqueda.setContenido(contenido);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha =  LocalDate.parse(criterios.getFecha(), formato);
        busqueda.setFecha(fecha);
        Ubicacion ubicacion = new Ubicacion(criterios.getLugar(),criterios.getLatitud(),criterios.getLongitud());
        busqueda.setUbicacion(ubicacion);
        busqueda.setFecha_carga(busqueda.getFecha_carga());
        busqueda.setOrigen_carga(busqueda.getOrigen_carga());
        return busqueda;
    }
}
