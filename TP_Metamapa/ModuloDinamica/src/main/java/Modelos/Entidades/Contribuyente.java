package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
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
    public LocalDate fecha_nacimiento;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Hecho> hechosPublicados;


    public Contribuyente(String usuario, String nombre, String apellido, LocalDate fechaNacimiento) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fechaNacimiento;
        this.hechosPublicados = new ArrayList<>();
    }

    public Contribuyente() {}

}