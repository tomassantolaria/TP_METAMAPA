package Modelos.Entidades;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "Hechos")
public class Hecho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idHecho;
    public Long idfuente;
    public String titulo;
    public String descripcion;
    @OneToOne
    @JoinColumn(name = "idContenido")
    public Contenido contenido;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    public Categoria categoria;
    public LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "idUbicacion")
    public Ubicacion ubicacion;
    @OneToMany
    @JoinColumn(name = "usuario")
    public Contribuyente contribuyente;
    public Boolean anonimo;
    public Boolean visible;

    public Hecho(Long idHecho, Long idfuente,String unTitulo, String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                 Ubicacion unaUbicacion, Contribuyente contribuyente, Boolean anonimo, boolean visible) {
        this.idHecho = idHecho;
        this.idfuente = idfuente;
        this.titulo = unTitulo;
        this.descripcion = unaDescripcion;
        this.contenido = unContenido;
        this.categoria = unaCategoria;
        this.fecha = unaFechaOcurrencia;
        this.ubicacion = unaUbicacion;
        this.visible = visible;
        this.contribuyente = contribuyente;
        this.anonimo = anonimo;
    }

    public void modificarVisibilidad(){
        visible = false;
    }

}