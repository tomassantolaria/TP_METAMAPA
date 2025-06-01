package Dinamica.Controller;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
//PATRON DTO
public class HechoContribuyenteDTO {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public Date fecha;
    public String lugar;
    public String latitud;
    public String longitud;
    //public String origen_carga;
    public String visible;
    public String nombre;
    public String apellido;
    public String fecha_Nacimiento;
    //lista de etiquetas
    public String anonimo;
}
