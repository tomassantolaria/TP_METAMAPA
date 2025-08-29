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
    @ManyToOne
    public List<Hecho> hechosPublicados;


}