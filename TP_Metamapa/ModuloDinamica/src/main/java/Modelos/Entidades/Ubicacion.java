package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUbicacion;
    private String nombre;
    private Double latitud;
    private Double longitud;

    public Ubicacion(String nombre, Double latitud, Double longitud) {}
}