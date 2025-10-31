package Modelos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime ;

@Getter
@Setter
public class HechoDTOInput {
    public String titulo;
    public String descripcion;
    public String contenido;
    public String contenido_multimedia;
    public String categoria;
    public LocalDateTime fechaAcontecimiento;
    public String localidad;
    public String provincia;
    public String pais ;
    public Double latitud;
    public Double longitud;
    public String usuario;
    public Boolean anonimo;
}
