package Controlador;

import Modelos.ColeccionDTO;
import Modelos.HechoDTO;
import Modelos.Entidades.HechoFilterInput;
import Servicio.NavegadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PublicoGraphQLController {

    @Autowired
    private NavegadorServicio navegadorServicio;

    @QueryMapping
    public List<HechoDTO> listarHechos(@Argument HechoFilterInput filtro) {

        if (filtro == null) {
            filtro = new HechoFilterInput();
        }

        return navegadorServicio.filtrarHechos(
                filtro.getIdColeccion(),
                filtro.getCategoria(),
                filtro.getContenidoMultimedia(),
                filtro.getFechaCargaDesde(),
                filtro.getFechaCargaHasta(),
                filtro.getFechaHechoDesde(),
                filtro.getFechaHechoHasta(),
                filtro.getOrigenCarga(),
                filtro.getTitulo(),
                filtro.getPais(),
                filtro.getProvincia(),
                filtro.getLocalidad(),
                filtro.getNavegacionCurada() != null ? filtro.getNavegacionCurada() : false // Default false
        );
    }

    @QueryMapping
    public List<ColeccionDTO> listarColecciones() {
        return navegadorServicio.obtenerColecciones();
    }
}