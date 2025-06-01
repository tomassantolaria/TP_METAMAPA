package Domain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.util.Map;
import java.util.List;

import java.util.HashMap;
import java.time.LocalDate;

@Getter
@Setter
public class Hecho{
    public static final Map<String, Hecho> hechosConTitulos = new HashMap<> ();
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

    protected Hecho(String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                    Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, String usuario, Boolean anonimo){ //Lista etiquetas
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

    public static Hecho getInstance(String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria,
                                    LocalDate unaFechaOcurrencia, Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible,
                                    String usuario, Boolean anonimo){

        Hecho hechoConEseTitulo = hechosConTitulos.get(unTitulo);
        if (hechoConEseTitulo == null) {
            hechoConEseTitulo = new Hecho (unTitulo,unaDescripcion,unContenido,unaCategoria,unaFechaOcurrencia,unaUbicacion,unaFechaCarga, unOrigen, estaVisible, usuario, anonimo);
            hechosConTitulos.put(unTitulo, hechoConEseTitulo);
        }else{
            hechoConEseTitulo.sobreescribirse(unaDescripcion, unContenido, unaCategoria, unaFechaOcurrencia, unaUbicacion, unaFechaCarga, unOrigen, estaVisible, usuario, anonimo);
        }
        return hechoConEseTitulo;
    }
    //para crear se hace el getInstance(...)
    public void sobreescribirse(String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                                Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, String usuario, Boolean anonimo){

    }
    public void admitirSOlicitudDeEliminacion(Solicitud unaSolicitud){

    }
    public void eliminarse(){
        visible = false;
    }

    public ContenidoMultimedia obtenerContenidoMultimedia(){
        return contenido.getContenidoMultimedia();
    }
    public void publicarConDatosPersonales(){

    }
    public void someterseARevision(){
        if(usuario != null ){

        }
    }
}