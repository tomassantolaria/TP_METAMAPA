package Servicio;

import Controlador.Colecciones.Coleccion;
import Controlador.CriterioDTO;
import Repositorio.AgregadorRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Controlador.Colecciones.*;
import Controlador.*;

@Service
public class AgregadorServicio {

    private final AgregadorRepositorio agregadorRepositorio;
    private final ColeccionServicio coleccionServicio;
    public AgregadorServicio(AgregadorRepositorio agregadorRepositorio, ColeccionServicio coleccionServicio) {

        this.agregadorRepositorio = agregadorRepositorio;
        this.coleccionServicio = coleccionServicio;
    }

    public List<HechoDTOOutput> filtrarHechos(@RequestBody CriterioDTO criterioDTO, Long id) {

        Coleccion coleccion = coleccionServicio.obtenerOCriarExcepcion(id);
        Busqueda criterios_busqueda = this.crearBusqueda(criterioDTO);
        Organizador organizador = new Organizador(coleccion, criterios_busqueda);
        List<Hecho> hechos_filtrados = new ArrayList<>();
        hechos_filtrados= organizador.filtrar();
        List<HechoDTOOutput> hechoDTOs = new ArrayList<>();
        hechoDTOs = this.transformarADTOLista(hechos_filtrados);
        return hechoDTOs;
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
    public void registrar(@RequestBody ContribuyenteDTOInput contribuyente){
        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyente);
        agregadorRepositorio.agregarContribuyente(contribuyente_listo);
    }
    public Contribuyente crearContribuyente(ContribuyenteDTOInput contribuyenteDTO){
        Contribuyente contribuyente = new Contribuyente();
        contribuyente.setUsuario( contribuyenteDTO.getUsuario());
        contribuyente.setNombre(contribuyenteDTO.getNombre());
        contribuyente.setApellido(contribuyenteDTO.getApellido());
        String fechaString = contribuyenteDTO.getFecha_nacimiento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        contribuyente.setFecha_nacimiento(fecha);
        return contribuyente;
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
    public List<HechoDTOOutput> mostrarTodosLosHechos(Long id){
        List<HechoDTOOutput> hechoDTO = new ArrayList<>();
        hechoDTO = this.transformarADTOLista(agregadorRepositorio.getHechos(id));
        return hechoDTO;
    }

}
