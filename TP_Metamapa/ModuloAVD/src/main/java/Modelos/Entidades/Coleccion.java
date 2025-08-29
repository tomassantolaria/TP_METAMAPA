package Modelos.Entidades;

import java.util.List;
import Servicio.Consenso.*;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Colecciones")
public class Coleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @OneToOne
    @JoinColumn()
    private CriteriosDePertenencia criterio_pertenencia;

    @ManyToMany
    @JoinTable()
    private List<Hecho> hechos;
    @ManyToOne
    @JoinColumn()
    private Consenso consenso;
    @ManyToMany
    @JoinTable()
    private List<Hecho> hechosConsensuados ;

    public Coleccion(Long id, String titulo, String descripcion, CriteriosDePertenencia criterio_pertenencia, List<Hecho> hechos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio_pertenencia = criterio_pertenencia;
        this.hechos = hechos;
        this.consenso = null;
        this.hechosConsensuados = hechos;
    }

    public Coleccion() {}





    public void eliminarHecho(Hecho unHecho) throws HechoNoPerteneceException {
        if (hechos.contains(unHecho)) {
            hechos.remove(unHecho);
        } else {
            throw new HechoNoPerteneceException();
        }
    }

    public void agregarHecho(Hecho unHecho) {
        if (! hechos.contains(unHecho)) {
            hechos.add(unHecho);
        }
    }

}




