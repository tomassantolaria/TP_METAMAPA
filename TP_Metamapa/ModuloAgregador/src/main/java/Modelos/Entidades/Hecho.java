package Modelos.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;



@Getter
@Setter
//@Entity
//@Table(name = "hechos")
public class Hecho{

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; //Cambiar a long
    public Long idFuente; // Cambiar a long
    public String titulo;
    public String descripcion;
    public Contenido contenido;
    public Categoria categoria;
    public LocalDate fecha;
    public Ubicacion ubicacion;
    public LocalDate fecha_carga;
    public OrigenCarga origen_carga; //enum
    public boolean visible ;
    public Contribuyente contribuyente; // no deberia ir contribuyente?
    public boolean anonimo;
    //public List<Etiqueta> etiquetas;

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
