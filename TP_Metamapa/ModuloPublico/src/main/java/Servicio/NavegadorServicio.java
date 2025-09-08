package Servicio;


import Modelos.Entidades.*;
import Modelos.HechoDTO;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavegadorServicio {

  @Autowired
  HechoRepositorio hechoRepositorio;
  ColeccionRepositorio coleccionRepositorio;


    public List<HechoDTO> filtrarHechos(Long idColeccion, String categoria, Boolean contenidoMultimedia, LocalDate fechaCargaDesde, LocalDate fechaCargaHasta, LocalDate fechaHechoDesde, LocalDate fechaHechoHasta, String origenCarga, String titulo, String pais, String provincia, String localidad) {
        List <Hecho> hechos;

        if(idColeccion == null){

            hechos = hechoRepositorio.filtrarHechos(categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCarga, titulo, pais, provincia, localidad);

        }else{

             hechos = coleccionRepositorio.filtrarHechosEnColeccion(idColeccion, categoria, contenidoMultimedia, fechaCargaDesde, fechaCargaHasta, fechaHechoDesde, fechaHechoHasta, origenCarga, titulo, pais, provincia, localidad);
        }

        return transformarADTOLista(hechos);
    }

    public List<HechoDTO> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTO> hechosDTO;
        hechosDTO = hechos.stream()
                .map(this::transformarAHechoDTO)
                .collect(Collectors.toList());
        return hechosDTO;
    }

    public HechoDTO transformarAHechoDTO (Hecho hecho){
        HechoDTO hechoDTO = new HechoDTO(hecho.getTitulo(),hecho.getDescripcion(), hecho.getContenido().getTexto(),hecho.getContenido().getContenido_multimedia(),hecho.getCategoria().getNombre(), hecho.getFecha(), hecho.getFecha_carga(), hecho.getUbicacion().getLocalidad().getNombre_localidad(), hecho.getUbicacion().getProvincia().getNombre_provincia(), hecho.getUbicacion().getPais().getNombre_pais(), hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud(), hecho.getContribuyente().getUsuario(), hecho.getContribuyente().getNombre(), hecho.getContribuyente().getApellido(), hecho.getContribuyente().getFecha_nacimiento(), hecho.isAnonimo(), hecho.isVisible(), hecho.getOrigen_carga().name());
        if (hecho.isAnonimo()) {
            hechoDTO.setUsuario(null);
        }
        return hechoDTO;
    }
}
