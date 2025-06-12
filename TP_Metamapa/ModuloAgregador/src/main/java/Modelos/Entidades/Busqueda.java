package Modelos.Entidades;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Busqueda extends CriteriosDePertenencia {


    String coleccionId;

    public Busqueda(String titulo, String descripcion, Contenido contenido, Categoria categoria, LocalDate fecha, Ubicacion ubicacion, LocalDate fecha_carga, OrigenCarga origen_carga, String coleccion) {
        super(titulo, descripcion, contenido, categoria, fecha, ubicacion, fecha_carga, origen_carga);
        this.coleccionId = coleccion;
    }

    public String getColeccion() { return coleccionId; }
}
