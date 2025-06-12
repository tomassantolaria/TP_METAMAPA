package Modelos.Entidades;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter

public class Hecho {
    public String id;
    public String titulo;
    public String descripcion;
    public Contenido contenido;
    public Categoria categoria;
    public LocalDate fecha;
    public Ubicacion ubicacion;
    public LocalDate fecha_carga;
    public OrigenCarga origen_carga; //enum
    public boolean visible;
    public Contribuyente contribuyente;
    public boolean anonimo;
    public List<Etiqueta> etiquetas;

    public Hecho(String id, String unTitulo, String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                 Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean visible, Contribuyente contribuyente, Boolean anonimo,
                 List<Etiqueta> etiquetas) {
        this.id = id;
        this.titulo = unTitulo;
        this.descripcion = unaDescripcion;
        this.contenido = unContenido;
        this.categoria = unaCategoria;
        this.fecha = unaFechaOcurrencia;
        this.ubicacion = unaUbicacion;
        this.fecha_carga = unaFechaCarga;
        this.origen_carga = unOrigen;
        this.visible = visible;
        this.contribuyente = contribuyente;
        this.anonimo = anonimo;
        this.etiquetas = etiquetas;
    }

    public void modificarVisibilidad(){
        visible = false;
    }

    //A modelar más adelante
    public void someterseAEtiquetado(Etiqueta etiqueta){}
}