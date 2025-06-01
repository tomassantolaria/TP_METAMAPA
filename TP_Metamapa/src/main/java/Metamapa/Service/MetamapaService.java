package Metamapa.Service;

import Domain.HechoDTO;
import Domain.*;
import Metamapa.Repository.MetamapaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.List;
import Metamapa.Controller.CriterioDTO;
import java.time.format.DateTimeFormatter;


@Service
public class MetamapaService {

    private final MetamapaRepository metamapaRepository;

    public MetamapaService(MetamapaRepository metamapaRepository) {

        this.metamapaRepository = metamapaRepository;
    }

    public List<HechoDTO> filtrarHechos(@RequestBody CriterioDTO criterioDTO, Long id) {
        Coleccion coleccion = coleccionService.obtenerOCriarExcepcion(id);
        Busqueda criterios_busqueda = this.crearBusqueda(criterioDTO);
        Organizador organizador = new Organizador();
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
    public void cargarHecho(HechoDTO hechoDTO){

    }
}
