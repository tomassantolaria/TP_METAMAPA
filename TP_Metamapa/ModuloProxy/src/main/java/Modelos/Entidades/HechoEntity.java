package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hechos_demo")
public class HechoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHecho;

    private Long idFuente;
    private String titulo;
    private String descripcion;
    private String contenido;
    private String contenido_multimedia;
    private String categoria;
    private LocalDate fechaAcontecimiento;
    private LocalDate fechaCarga;
    private String localidad;
    private String provincia;
    private String pais;
    private Double latitud;
    private Double longitud;
    private String usuario;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private Boolean anonimo;
    private Boolean visible;
    private String origen_carga;
    private String urlFuente;
}