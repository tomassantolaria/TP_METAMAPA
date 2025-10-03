package Servicios;

import Modelos.DTOs.HechoDTO;
import Modelos.Entidades.HechoEntity;
import Repositorios.HechoDemoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class FuenteDemoService {

    @Autowired
    ConexionService conexionService;
    @Autowired
    HechoDemoRepositorio hechoDemoRepositorio;

    private LocalDateTime ultimaConsulta;
    private final URL url;

    public FuenteDemoService() {
        this.ultimaConsulta = null;
        try {
            this.url = new URL("https://fuente-demo.org/api/hechos");
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL inválida", e);
        }
    } 


    public List<HechoDTO> obtenerHecho() {
    return hechoDemoRepositorio.findAll().stream().map(this::entityToDTO).toList();
    }



    public void actualizarHechos() {
    
    Map<String, Object> data;
    List<HechoEntity> nuevosHechos = new ArrayList<>();

    while ((data = conexionService.siguienteHecho(url, ultimaConsulta)) != null) {
        HechoEntity entity = mapToEntity(data);
        nuevosHechos.add(entity);
    }

    if (!nuevosHechos.isEmpty()) {
        hechoDemoRepositorio.saveAll(nuevosHechos);
    }

    ultimaConsulta = LocalDateTime.now();

    }

    private HechoEntity mapToEntity(Map<String, Object> data) {
        HechoEntity entity = new HechoEntity();
        entity.setTitulo((String) data.get("titulo"));
        entity.setDescripcion((String) data.get("descripcion"));
        entity.setContenido((String) data.get("contenido"));
        entity.setContenido_multimedia((String) data.get("contenidoMultimedia"));
        entity.setCategoria((String) data.get("categoria"));
        entity.setFechaAcontecimiento((java.time.LocalDate) data.get("fecha"));
        entity.setPais((String) data.get("pais"));
        entity.setProvincia((String) data.get("provincia"));
        entity.setLocalidad((String) data.get("localidad"));
        entity.setLatitud((Double) data.get("latitud"));
        entity.setLongitud((Double) data.get("longitud"));
        entity.setUrlFuente(url.toString());
        return entity;
    }

    private HechoDTO entityToDTO(HechoEntity entity) {
        HechoDTO dto = new HechoDTO();
        dto.setIdHecho(entity.getIdHecho());
        dto.setIdFuente(entity.getIdFuente());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setContenido(entity.getContenido());
        dto.setContenido_multimedia(entity.getContenido_multimedia());
        dto.setCategoria(entity.getCategoria());
        dto.setFechaAcontecimiento(entity.getFechaAcontecimiento());
        dto.setFechaCarga(entity.getFechaCarga());
        dto.setLocalidad(entity.getLocalidad());
        dto.setProvincia(entity.getProvincia());
        dto.setPais(entity.getPais());
        dto.setLatitud(entity.getLatitud());
        dto.setLongitud(entity.getLongitud());
        dto.setUsuario(entity.getUsuario());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setFecha_nacimiento(entity.getFecha_nacimiento());
        dto.setAnonimo(entity.getAnonimo());
        dto.setVisible(entity.getVisible());
        dto.setOrigen_carga(entity.getOrigen_carga());
        return dto;
    }

    // @Override
    // public List<HechoDTO> obtenerHecho() {
    //     return fuenteDemoHechos.obtenerHechos();
    // }

//    @Override
//    public List<HechoDTO> obtenerHecho() {
//        List<HechoDTO> hechos = fuenteDemoHechos.obtenerHechos();
//        return convertirADTO(hechos);
//    }

    // @Override
    // public void actualizarHechos() {
    //     List<HechoDTO> nuevosHechos = new ArrayList<>();
    //     Map<String, Object> data;

    //     while((data = conexionService.siguienteHecho(url, ultimaConsulta)) != null){
    //         HechoDTO dto = convertirDTODesdeMap(data);
    //         nuevosHechos.add(dto);
    //     }
    //     fuenteDemoHechos.guardarHechos(url.toString(), nuevosHechos);
    //     ultimaConsulta = LocalDateTime.now(); // Actualizar la fecha de la última consulta
    // }

    private HechoDTO convertirDTODesdeMap(Map<String, Object> data) {
        return new HechoDTO(
                (String) data.get("titulo"),
                (String) data.get("descripcion"),
                (String) data.get("contenido"),
                (String) data.get("contenidoMultimedia"),
                (String) data.get("categoria"),
                (LocalDate) data.get("fecha"), // Si tu JSON devuelve esto como string, hay que parsearlo
                null, // fechaAcontecimiento, si no viene en el JSON
                (String) data.get("pais"),
                (String) data.get("provincia"),
                (String) data.get("localidad"),
                (Double) data.get("latitud"),
                (Double) data.get("longitud"),
                null, null, null, null, null, null, null
        );
    }

}