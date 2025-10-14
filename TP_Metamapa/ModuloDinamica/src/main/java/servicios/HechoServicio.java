package servicios;

import Modelos.HechoDTO;
import Modelos.HechoDTOInput;
import Repositorios.*;
import Modelos.Entidades.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.List;

@Service
public class HechoServicio {

    @Autowired
    HechoRepositorio hechoRepositorio;
    @Autowired
    ContribuyenteRepositorio contribuyenteRepositorio;
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    @Autowired
    ProvinciaRepositorio provinciaRepositorio;
    @Autowired
    PaisRepositorio paisRepositorio;
    @Autowired
    LocalidadRepositorio localidadRepositorio;
    @Autowired
    UbicacionRepositorio ubicacionRepositorio;

    public void crearHecho(HechoDTOInput dto) {
        Categoria categoria = this.crearCategoria(dto.getCategoria());
        Contenido contenido = new Contenido(dto.getContenido(),dto.getContenido_multimedia());
        Pais pais = this.crearPais(dto.getPais());
        Provincia provincia = this.crearProvincia(dto.getProvincia(), pais);
        Localidad localidad = this.crearLocalidad(dto.getLocalidad(), provincia);
        Ubicacion ubicacion = this.crearUbicacion(dto.getLatitud(), dto.getLongitud(), localidad, provincia, pais);
        LocalDateTime fechaOcurrencia =  dto.getFechaAcontecimiento();
        Contribuyente contribuyente = this.crearContribuyente(dto.getUsuario());
        boolean anonimo = dto.getAnonimo();

        Hecho hecho = new Hecho(null,contribuyente.getId(), dto.getTitulo(), dto.getDescripcion(), contenido, categoria, fechaOcurrencia, ubicacion,
                                contribuyente, anonimo, true, false);
        hechoRepositorio.save(hecho);
    }

    public Categoria crearCategoria(String nombre) {
        Categoria categoria = categoriaRepositorio.findByNombre(nombre);
        if(categoria == null){
            categoria = new Categoria(nombre);
            categoriaRepositorio.save(categoria);
        }
        return categoria;
    }

    public Contribuyente crearContribuyente( String usuario){
        Contribuyente contribuyente = contribuyenteRepositorio.findByUsuario(usuario);
        if(contribuyente == null){
            throw new RuntimeException("No existe el contribuyente con el usuario " + usuario + "debe registrarse antes de crear un hecho") ;
        }
        return contribuyente;
    }

    public Pais crearPais(String nombre) {
        Pais pais = paisRepositorio.findByPais(nombre);
        if(pais == null){
            pais = new Pais(nombre);
            paisRepositorio.save(pais);
        }
        return pais;
    }

    public Provincia crearProvincia(String nombre, Pais pais) {
        Provincia provincia = provinciaRepositorio.findByProvinciaAndPais(nombre, pais);
        if(provincia == null){
            provincia = new Provincia(nombre, pais);
            provinciaRepositorio.save(provincia);
        }
        return provincia;
    }

    public Localidad crearLocalidad(String nombre, Provincia provincia) {
        Localidad localidad = localidadRepositorio.findByLocalidadAndProvincia(nombre, provincia);
        if(localidad == null){
            localidad = new Localidad(nombre, provincia);
            localidadRepositorio.save(localidad);
        }
        return localidad;
    }

    public Ubicacion crearUbicacion(Double latitud, Double longitud, Localidad localidad, Provincia provincia, Pais pais) {
        Ubicacion ubicacion = ubicacionRepositorio.findByLatitudAndLongitud(latitud, longitud);
        if(ubicacion == null){
            ubicacion = new Ubicacion(localidad, provincia, pais, latitud, longitud);
            ubicacionRepositorio.save(ubicacion);
        }
        return ubicacion;
    }

    @Transactional
    public List<HechoDTO> obtenerHechos() {
        List<Hecho> hechos = hechoRepositorio.findByPublicadoFalse();
        hechos.forEach(h -> h.setPublicado(true));
        hechoRepositorio.saveAll(hechos);
        return transformarADTOLista(hechos);
    }

    public List<HechoDTO> transformarADTOLista(List<Hecho> hechos) {
        List<HechoDTO> hechosDTO = new ArrayList<>();
        for (Hecho hecho : hechos) {
            HechoDTO dto = new HechoDTO(
                    null,
                    hecho.getIdfuente(),
                    hecho.getTitulo(),
                    hecho.getDescripcion(),
                    hecho.getContenido().getTexto(),
                    hecho.getContenido().getContenido_multimedia(),
                    hecho.getCategoria().getNombre(),
                    hecho.getFecha(),
                    null,
                    hecho.getUbicacion().getLocalidad().getLocalidad(),
                    hecho.getUbicacion().getProvincia().getProvincia(),
                    hecho.getUbicacion().getPais().getPais(),
                    hecho.getUbicacion().getLatitud(),
                    hecho.getUbicacion().getLongitud(),
                    hecho.getContribuyente().getUsuario(),
                    hecho.getContribuyente().getNombre(),
                    hecho.getContribuyente().getApellido(),
                    hecho.getContribuyente().getFecha_nacimiento(),
                    hecho.getAnonimo(),
                    hecho.getVisible(),
                    null
            );
            hechosDTO.add(dto);
        }
        return hechosDTO;
    }

}