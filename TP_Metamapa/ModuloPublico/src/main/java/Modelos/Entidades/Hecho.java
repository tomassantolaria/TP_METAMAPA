package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name="Hecho")
public class Hecho{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long idFuente;
    public String titulo;
    public String descripcion;

    @OneToOne
    @JoinColumn()
    public Contenido contenido;

    @ManyToOne
    @JoinColumn()
    public Categoria categoria;

    public LocalDate fecha;

    @ManyToOne
    @JoinColumn()
    public Ubicacion ubicacion;

    public LocalDate fecha_carga;

    @Enumerated(EnumType.STRING)
    @Column()
    public OrigenCarga origen;

    public Boolean visible;

    @ManyToOne
    @JoinColumn()
    private Contribuyente contribuyente;

    public Boolean anonimo ;

    public Hecho(Long id, Long idFuente, String unTitulo, String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                 Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, Boolean estaVisible, Contribuyente contribuyente, Boolean anonimo){ //Lista etiquetas
        this.id = id;
        this.idFuente = idFuente;
        this.titulo = unTitulo;
        this.descripcion = unaDescripcion;
        this.contenido = unContenido;
        this.categoria = unaCategoria;
        this.fecha = unaFechaOcurrencia;
        this.ubicacion = unaUbicacion;
        this.fecha_carga = unaFechaCarga;
        this.origen = unOrigen;
        this.visible = estaVisible;
        this.contribuyente = contribuyente;
        this.anonimo = anonimo;
        //this.etiquetas = etiquetas;
    }
    public Hecho(){}

}
