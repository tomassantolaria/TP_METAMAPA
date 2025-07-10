package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Contribuyente {
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;

    public Contribuyente(String usuario, String nombre, String apellido, LocalDate fecha_nacimiento){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
    }
}