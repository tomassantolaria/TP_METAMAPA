package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import Modelos.Entidades.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "HechosDemo")
public class HechoDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHecho;
    @ManyToOne
    @JoinColumn()
    private Fuente fuente;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String contenido_multimedia;
    private String categoria;
    private LocalDate fechaAcontecimiento;
    private LocalDate fechaCarga;
    private String localidad;
    private String provincia;
    private String pais;
    private Double latitud;
    private Double longitud;
    private Boolean publicado;


    public HechoDemo(){}
}