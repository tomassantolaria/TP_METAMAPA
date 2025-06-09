package Servicio;

import Controlador.Modelos.Entidades.Coleccion;
import Controlador.Modelos.DTOs.CriterioDTO;
import Controlador.Modelos.DTOs.HechoDTOOutput;
import Controlador.Modelos.Entidades.*;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiltroServicio {

    @Autowired
    private ColeccionServicio coleccionServicio;
    private HechoRepositorio hechoRepositorio;

    public List<HechoDTOOutput> filtrarHechosDe(String id, @RequestBody CriterioDTO criterioDTO) {
        Coleccion coleccion = coleccionServicio.obtenerOCriarExcepcion(id);
        return this.filtrarHechos(coleccion.getHechos(),criterioDTO);
    }

    public List<HechoDTOOutput> filtrar(CriterioDTO criterioDTO) {
        return this.filtrarHechos(hechoRepositorio.getHechos(), criterioDTO);
    }

    public List<HechoDTOOutput> filtrarHechos(List<Hecho> hechos, CriterioDTO criterioDTO) {
        Criterios criterios_busqueda = this.crearBusqueda(criterioDTO);
        Organizador organizador = new Organizador(hechos, criterios_busqueda);
        List<Hecho> hechos_filtrados = new ArrayList<>();
        hechos_filtrados= organizador.filtrar();
        return this.transformarADTOLista(hechos_filtrados);
    }

    private Criterios crearBusqueda(CriterioDTO criterios) {
        Categoria categoria = new Categoria(criterios.getCategoria());
        ContenidoMultimedia contenido_multimedia = new ContenidoMultimedia(criterios.getContenido_multimedia());
        Contenido contenido = new Contenido(criterios.getContenido_texto(),contenido_multimedia);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha =  LocalDate.parse(criterios.getFecha(), formato);
        LocalDate fecha_carga =  LocalDate.parse(criterios.getFecha_carga(), formato);
        Ubicacion ubicacion = new Ubicacion(criterios.getLugar(),criterios.getLatitud(),criterios.getLongitud());
        Criterios busqueda = new Criterios(criterios.getTitulo(),criterios.getDescripcion(), contenido,categoria, fecha, ubicacion,fecha_carga,OrigenCarga.valueOf(criterios.getOrigen_carga()));
        return busqueda;
    }

    public List<HechoDTOOutput> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTOOutput> hechosDTO = new ArrayList<>();
        hechosDTO = hechos.stream()
                .map(this::transformarHechoADTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTOOutput transformarHechoADTO(Hecho hecho){
        HechoDTOOutput hechoDTO = new HechoDTOOutput();
        hechoDTO.setTitulo(hecho.getTitulo());
        hechoDTO.setDescripcion(hecho.getDescripcion());
        Categoria categoria = hecho.getCategoria();
        hechoDTO.setCategoria(categoria.getNombre());
        Contenido contenido = hecho.getContenido();
        hechoDTO.setContenido(contenido.getTexto());
        hechoDTO.setContenido_multimedia(hechoDTO.getContenido_multimedia());
        LocalDate fecha = hecho.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaString = fecha.format(formatter);
        hechoDTO.setFecha(fechaString);
        Ubicacion ubicacion = hecho.getUbicacion();
        hechoDTO.setLugar(ubicacion.getNombre());
        return hechoDTO;
    }
    public List<HechoDTOOutput> mostrarTodosLosHechosDe(String id){
        Coleccion coleccion = coleccionServicio.obtenerOCriarExcepcion(id);
        return this.transformarADTOLista(coleccion.getHechos());
    }

    public List<HechoDTOOutput> mostrarTodosLosHechos(){
        return this.transformarADTOLista(hechoRepositorio.getHechos());
    }
}
