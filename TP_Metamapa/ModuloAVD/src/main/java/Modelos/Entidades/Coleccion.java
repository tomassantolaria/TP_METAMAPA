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
    private List<Hecho> hechos = new ArrayList<>();
    @Column(nullable = true)
    @Convert(converter = ConsensoConversor.class)
    private Consenso consenso;
    @ManyToMany()
    @JoinTable()
    private List<Hecho> hechosConsensuados = new ArrayList<>();

    public Coleccion(String titulo, String descripcion,Consenso consenso ,CriteriosDePertenencia criterio_pertenencia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.criterio_pertenencia = criterio_pertenencia;
        this.hechos = new ArrayList<>();
        this.consenso = consenso;
        this.hechosConsensuados = new ArrayList<>();
    }

    public Coleccion() {
    }


    public void agregarHecho(Hecho unHecho) {
        if (! hechos.contains(unHecho)) {
            hechos.add(unHecho);
        }
    }

}




