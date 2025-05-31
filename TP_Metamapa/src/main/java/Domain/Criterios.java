package Domain;
import java.time.LocalDate;

public class Criterios {
    String titulo;
    String descripcion;
    Contenido contenido;
    Categoria categoria;
    LocalDate fecha;
    Ubicacion ubicacion;
    LocalDate fecha_carga;
    OrigenCarga origen_carga;
    public String getTitulo() { return titulo; }
    public String getDescripcion() {return descripcion;}
    public Categoria getCategoria() { return categoria; }
    public OrigenCarga getOrigenCarga() { return origen_carga; }
    public Contenido getContenido() { return contenido; }
    public Ubicacion getUbicacion() { return ubicacion; }
    public LocalDate getFecha() { return fecha; }
    public LocalDate getFecha_carga() { return fecha_carga; }

}
