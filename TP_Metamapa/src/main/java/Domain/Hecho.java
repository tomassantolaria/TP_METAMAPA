package Domain;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


import java.util.HashMap;
import java.util.Date;

@Getter
@Setter
public class Hecho{
    private static final Map<String, Hecho> hechosConTitulos = new HashMap<> ();
    public String titulo;
    public String descripcion;
    public Contenido contenido;
    public Categoria categoria;
    public Date fecha;
    public Ubicacion ubicacion;
    public Date fecha_carga;
    public OrigenCarga origen_carga; //enum
    public boolean visible;

    protected Hecho(String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria, Date unaFechaOcurrencia, Ubicacion unaUbicacion, Date unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible){
        this.titulo = unTitulo;
        this.descripcion = unaDescripcion;
        this.contenido = unContenido;
        this.categoria = unaCategoria;
        this.fecha = unaFechaOcurrencia;
        this.ubicacion = unaUbicacion;
        this.fecha_carga = unaFechaCarga;
        this.origen_carga = unOrigen;
        this.visible = estaVisible;
    }

    public static Hecho getInstance(String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria, Date unaFechaOcurrencia, Ubicacion unaUbicacion, Date unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible){
        Hecho hechoConEseTitulo = hechosConTitulos.get(unTitulo);
        if (hechoConEseTitulo == null)
        {
            hechoConEseTitulo = new Hecho (unTitulo,unaDescripcion,unContenido,unaCategoria,unaFechaOcurrencia,unaUbicacion,unaFechaCarga, unOrigen, estaVisible);
        }
        else
        {
            hechoConEseTitulo.sobreescribirse(unaDescripcion, unContenido, unaCategoria, unaFechaOcurrencia, unaUbicacion, unaFechaCarga, unOrigen, estaVisible);
        }
        return hechoConEseTitulo;
    }
    //para crear se hace el getInstance(...)
    public void sobreescribirse(String unaDescripcion, Contenido unContenido, Categoria unaCategoria, Date unaFechaOcurrencia, Ubicacion unaUbicacion, Date unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible){

    }
    public void admitirSOlicitudDeEliminacion(Solicitud unaSolicitud){

    }
    public void eliminarse(){
        visible = false;
    }

}