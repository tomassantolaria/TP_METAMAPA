package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@Entity
@Table(name = "Hechos")
public class Hecho{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Cambiar a long

    private Long idFuente; // Cambiar a long
    private String titulo;
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "contenido_id")
    private Contenido contenido;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    private LocalDate fecha_carga;

    @Enumerated(EnumType.STRING)
    @Column(name = "origen_carga")
    private OrigenCarga origen_carga; //enum

    private boolean visible ;

    @ManyToOne
    @JoinColumn(name = "contribuyente_usuario")
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


    public void eliminarse(){
        visible = false;
    }

    //public void someterseARevision(){}

    public Boolean esIgualA(Hecho otroHecho) {
        return this.titulo.equals(otroHecho.titulo) &&
                this.descripcion.equals(otroHecho.descripcion) &&
                this.contenido.getContenido_multimedia().equals(otroHecho.contenido.getContenido_multimedia()) && // ver
                this.contenido.getTexto().equals(otroHecho.contenido.getTexto()) && // ver
                this.categoria.getNombre().equals(otroHecho.categoria.getNombre()) && // ver
                this.fecha.equals(otroHecho.fecha) &&
                this.ubicacion.getLatitud().equals(otroHecho.ubicacion.getLatitud()) &&
                this.ubicacion.getLongitud().equals(otroHecho.ubicacion.getLongitud());
    }
}
