package Modelos.Entidades;

import java.util.ArrayList;
import java.util.List;

import Modelos.Conversores.ConsensoConversor;
import Modelos.Entidades.Consenso.Consenso;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Coleccion")
public class Coleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;

    @OneToOne()
    @JoinColumn()
    private CriteriosDePertenencia criterio_pertenencia;

    @ManyToMany()
    @JoinTable()
    private List<Hecho> hechos;
    @ManyToOne
    @JoinColumn()
    @Convert(converter = ConsensoConversor.class)
    private Consenso consenso;
    @ManyToMany()
    @JoinTable()
    private List<Hecho> hechosConsensuados ;

    public Coleccion(String titulo, String descripcion, CriteriosDePertenencia criterio_pertenencia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio_pertenencia = criterio_pertenencia;
        this.hechos = new ArrayList<>();
        this.consenso = null;
        this.hechosConsensuados = new ArrayList<>();
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




