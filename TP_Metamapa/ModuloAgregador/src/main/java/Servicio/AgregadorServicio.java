package Servicio;

import com.TP_Metamapa.*;
import Controlador.CriterioDTO;
import Repositorio.AgregadorRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Controlador.*;
@Service
public class AgregadorServicio {

    private final AgregadorRepositorio agregadorRepositorio;

    public AgregadorServicio(AgregadorRepositorio agregadorRepositorio) {

        this.agregadorRepositorio = agregadorRepositorio;
    }
    public List<HechoDTO> filtrarHechos(@RequestBody CriterioDTO criterioDTO, Long id) {
        Coleccion coleccion = coleccionService.obtenerOCriarExcepcion(id);
        Busqueda criterios_busqueda = this.crearBusqueda(criterioDTO);
        Organizador organizador = Organizador(coleccion, criterios_busqueda);
        List<Hecho> hechos_filtrados = new ArrayList<>();
        hechos_filtrados= organizador.filtrar();
        List<HechoDTO> hechoDTOs = new ArrayList<>();
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
    public void registrar(@RequestBody ContribuyenteDTO contribuyente){
        Contribuyente contribuyente_listo = this.crearContribuyente(contribuyente);
        metamapaRepository.agregarContribuyente(contribuyente_listo);
    }
    public Contribuyente crearContribuyente(ContribuyenteDTO contribuyenteDTO){
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


}
