package Controlador.Modelos.Entidades;

import java.time.LocalDate;

public class CriterioDePertenencia extends Criterios {
    int idFuente;

    public CriterioDePertenencia(String titulo, String descripcion, Contenido contenido, Categoria categoria, LocalDate fecha, Ubicacion ubicacion, LocalDate fecha_carga, OrigenCarga origen_carga, String idFuente) {
        super(titulo, descripcion, contenido, categoria, fecha, ubicacion, fecha_carga, origen_carga);
        this.idFuente = Integer.parseInt(idFuente);
    }

    public int getFuente() { return idFuente; }

}
