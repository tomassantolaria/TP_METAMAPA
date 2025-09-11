package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@Entity
@Table(name = "Hecho")
public class Hecho{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Cambiar a long

    private Long idFuente; // Cambiar a long
    private String titulo;
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Contenido contenido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Categoria categoria;

    private LocalDate fecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Ubicacion ubicacion;

    private LocalDate fecha_carga;

    @Enumerated(EnumType.STRING)
    @Column()
    private OrigenCarga origen_carga; //enum

    private boolean visible ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Contribuyente contribuyente;

    private boolean anonimo;


    public Hecho() {}

    public Hecho(Long id, Long idFuente, String unTitulo, String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                 Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, Contribuyente contribuyente, Boolean anonimo){ //Lista etiquetas
        this.id = id;
        this.idFuente = idFuente;
        this.titulo = unTitulo;
        this.descripcion = unaDescripcion;
        this.contenido = unContenido;
        this.categoria = unaCategoria;
        this.fecha = unaFechaOcurrencia;
        this.ubicacion = unaUbicacion;
        this.fecha_carga = unaFechaCarga;
        this.origen_carga = unOrigen;
        this.visible = estaVisible;
        this.contribuyente = contribuyente;
        this.anonimo = anonimo;
        //this.etiquetas = etiquetas;
    }


}
