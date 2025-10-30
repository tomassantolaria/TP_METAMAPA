package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.time.LocalDateTime ;

@Setter
@Getter
@Entity
@Table(name = "Contribuyente")
public class Contribuyente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    public String keycloakId;

    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDateTime fecha_nacimiento;



    public Contribuyente(String usuario, String nombre, String apellido, LocalDateTime fechaNacimiento) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fechaNacimiento;
    }

    public Contribuyente() {}

}