package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Hecho{
    public String id;
    public String titulo;
    public String descripcion;
    public Contenido contenido;
    public Categoria categoria;
    public LocalDate fecha;
    public Ubicacion ubicacion;
    public LocalDate fecha_carga;
    public OrigenCarga origen_carga; //enum
    public boolean visible = false;
    public String usuario;
    public boolean anonimo = false;
    //public List<Etiqueta> etiquetas;

    protected Hecho(String id, String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                    Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, String usuario, Boolean anonimo){ //Lista etiquetas
        this.id = id;
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
}
