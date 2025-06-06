package Modelos.Entidades;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import java.util.HashMap;
import java.time.LocalDate;
import java.util.List;
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
    public Contribuyente contribuyente;
    public boolean anonimo = false;
    public List<Etiqueta> etiquetas;

    protected Hecho(String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                    Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, Contribuyente contribuyente, Boolean anonimo,
                    List<Etiqueta> etiquetas ){
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
        this.etiquetas = etiquetas;
    }


    public static Hecho getInstance(String unTitulo , String unaDescripcion, Contenido unContenido, Categoria unaCategoria,
                                    LocalDate unaFechaOcurrencia, Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible,
                                    Contribuyente contribuyente, Boolean anonimo, List<Etiqueta> etiquetas){

        Hecho hechoConEseTitulo = hechosConTitulos.get(unTitulo);
        if (hechoConEseTitulo == null) {
            hechoConEseTitulo = new Hecho (unTitulo,unaDescripcion,unContenido,unaCategoria,unaFechaOcurrencia,unaUbicacion,unaFechaCarga,
                                            unOrigen, estaVisible, contribuyente, anonimo, etiquetas);
            hechosConTitulos.put(unTitulo, hechoConEseTitulo);
        }else{
            hechoConEseTitulo.sobreescribirse(unaDescripcion, unContenido, unaCategoria, unaFechaOcurrencia,
                    unaUbicacion, unaFechaCarga, unOrigen, estaVisible, contribuyente, anonimo, etiquetas);
        }
        return hechoConEseTitulo;
    }
    //para crear se hace el getInstance(...)

    public void sobreescribirse(String unaDescripcion, Contenido unContenido, Categoria unaCategoria, LocalDate unaFechaOcurrencia,
                                Ubicacion unaUbicacion, LocalDate unaFechaCarga, OrigenCarga unOrigen, boolean estaVisible, Contribuyente contribuyente, Boolean anonimo,
                                List<Etiqueta> etiquetas){
    }

    //public void admitirSolicitudDeEliminacion(Solicitud unaSolicitud){;}

    public void eliminarse(){
        visible = false;
    }

