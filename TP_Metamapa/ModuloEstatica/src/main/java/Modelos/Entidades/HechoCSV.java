package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter

public class HechoCSV {
    public static final Map<String, HechoCSV> hechosConTitulos = new HashMap<>();

    private String titulo;
    private String descripcion;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private LocalDate fechaAcontecimiento;


    protected HechoCSV(String unTitulo, String unaDescripcion, String unaCategoria, LocalDate unaFechaAcontecimiento,
                       Double latitud, Double longitud) {
        this.titulo = unTitulo;
        this.descripcion = unaDescripcion;
        this.categoria = unaCategoria;
        this.fechaAcontecimiento = unaFechaAcontecimiento;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public static HechoCSV getInstance(String unTitulo, String unaDescripcion, String unaCategoria, LocalDate unaFechaAcontecimiento,
                                       Double latitud, Double longitud) {

        HechoCSV hechoConEseTitulo = hechosConTitulos.get(unTitulo);
        if (hechoConEseTitulo == null) {
            hechoConEseTitulo = new HechoCSV(unTitulo, unaDescripcion, unaCategoria, unaFechaAcontecimiento, latitud, longitud);
            hechosConTitulos.put(unTitulo, hechoConEseTitulo);
        } else {
            hechoConEseTitulo.sobreescribirse(unTitulo, unaDescripcion, unaCategoria, unaFechaAcontecimiento, latitud, longitud);
        }
        return hechoConEseTitulo;
    }
    //para crear se hace el getInstance(...)

    public void sobreescribirse(String unTitulo, String unaDescripcion, String unaCategoria, LocalDate unaFechaAcontecimiento,
                                Double latitud, Double longitud) {
    }
    //public void admitirSolicitudDeEliminacion(Solicitud unaSolicitud){;}

}



