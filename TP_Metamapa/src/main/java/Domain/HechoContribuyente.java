package Domain;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class HechoContribuyente extends Hecho{

    public Contribuyente contribuyente;
    public List<Etiqueta> etiquetasHecho;
    public boolean anonimo;

    public HechoContribuyente(String titulo, String descripcion, Contenido contenido, Categoria categoria, Date fecha,
                              Ubicacion ubicacion, Date fecha_carga, OrigenCarga origen_carga, boolean visible, Contribuyente contribuyente,
                              List<Etiqueta> etiquetasHecho, boolean anonimo){
        super(titulo, descripcion, contenido, categoria, fecha, ubicacion, fecha_carga, origen_carga, visible);
        this.contribuyente = contribuyente;
        this.etiquetasHecho = etiquetasHecho;
        this.anonimo = anonimo;
    }
    /*
    public static HechoContribuyente getInstance(String titulo, String descripcion, Contenido contenido,
                                             Categoria categoria, Date fecha, Ubicacion ubicacion,
                                             Date fechaCarga, OrigenCarga origenCarga, boolean visible,
                                             Contribuyente contribuyente, List<Etiqueta> etiquetasHecho, boolean anonimo) {

        HechoContribuyente hechoConEseTitulo = hechosConTitulos.get(titulo);  //ME QUIERO MORIR

    }*/

    public void publicarConDatosPersonales(){

    }
    public void someterseARevision(){

    }
}
