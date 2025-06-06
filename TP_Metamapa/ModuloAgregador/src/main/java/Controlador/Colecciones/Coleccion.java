package Controlador.Colecciones;

import java.util.List;

import Controlador.CriterioDePertenencia;
import Controlador.Hecho;
import Domain.HechoNoPerteneceException;
import Domain.HechoYaExisteException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coleccion {
    private Long id;
    private String titulo;
    private String descripcion;
    private CriterioDePertenencia criterio_pertenencia;
    private List<Hecho> hechos;
    private int idFuente;

    public void eliminarHecho(Hecho unHecho) {
        if (hechos.contains(unHecho)) {
            hechos.remove(unHecho);
        } else {
            throw new Controlador.Colecciones.HechoNoPerteneceException("El hecho no pertenece a la colección");
        }
    }

    public void agregarHecho(Hecho unHecho) {
        if (hechos.contains(unHecho)) {
            throw new HechoYaExisteException("El hecho ya pertenece a la colección");
        } else {
            hechos.add(unHecho);
        }
    }
}




