package Modelos.Entidades;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter

public class Hecho {
    public Long idHecho;
    public Long idfuente;
    public String titulo;
    public String descripcion;
    public Contenido contenido;
    public Categoria categoria;
    public LocalDate fecha;
    public Ubicacion ubicacion;
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