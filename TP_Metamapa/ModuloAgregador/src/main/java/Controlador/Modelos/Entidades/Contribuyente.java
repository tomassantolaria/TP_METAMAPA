package Controlador.Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Contribuyente {
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;

    public void solicitarEliminacionDeHecho(Hecho unHecho){

    }
}
