package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class Hecho{
    public UUID id;
    public UUID idFuente;
    public String titulo;
    public String descripcion;
    public Contenido contenido;
    public Categoria categoria;
    public LocalDate fecha;
    public Ubicacion ubicacion;
    public LocalDate fecha_carga;
    public OrigenCarga origen_carga; //enum
    public boolean visible = true;
    public String usuario;
    public boolean anonimo = true;
    //public List<Etiqueta> etiquetas;

    public Hecho(UUID id, UUID idFuente, String unTitulo, String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                 Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, String usuario, Boolean anonimo){ //Lista etiquetas
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
        this.usuario = usuario;
        this.anonimo = anonimo;
        //this.etiquetas = etiquetas;
    }


    public void eliminarse(){
        visible = false;}

    public void someterseARevision(){}

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
