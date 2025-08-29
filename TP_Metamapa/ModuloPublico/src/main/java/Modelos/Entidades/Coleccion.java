package Modelos.Entidades;

import java.util.List;
import java.util.UUID;



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

}




