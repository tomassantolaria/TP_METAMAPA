package Modelos.Entidades;

import java.util.List;
import java.util.UUID;
import Servicio.Consenso.*;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coleccion {
    private UUID id;
    private String titulo;
    private String descripcion;
    private CriteriosDePertenencia criterio_pertenencia;
    private List<Hecho> hechos;
    private Consenso consenso;
    private List<Hecho> hechosconsensuados ;

    public Coleccion(UUID id, String titulo, String descripcion, CriteriosDePertenencia criterio_pertenencia, List<Hecho> hechos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio_pertenencia = criterio_pertenencia;
        this.hechos = hechos;
        this.consenso = null;
        this.hechosconsensuados = hechos;
    }


    public void eliminarHecho(Hecho unHecho) throws HechoNoPerteneceException {
        if (hechos.contains(unHecho)) {
            hechos.remove(unHecho);
        } else {
            throw new HechoNoPerteneceException();
        }
    }

    public void agregarHecho(Hecho unHecho) {
        if (hechos.contains(unHecho)) {
            throw new HechoYaExisteException(); //deberia tirar error?
        } else {
            hechos.add(unHecho);
        }
    }


}




