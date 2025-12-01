package Modelos.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "Contribuyente")
public class Contribuyente {
    @Id
    public String usuario;
    public String nombre;
    public String apellido;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate fecha_nacimiento;

    public Contribuyente(String usuario, String nombre, String apellido, LocalDate fecha_nacimiento) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Contribuyente(){}


}
