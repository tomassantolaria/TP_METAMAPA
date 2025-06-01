package Domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
//PATRON DTO
public class HechoDTO {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public String fecha;
    public String lugar;
    public String latitud;
    public String longitud;
    //public String origen_carga;
    public String visible;
    public String usuario;
    //lista de etiquetas
    public String anonimo;
}
