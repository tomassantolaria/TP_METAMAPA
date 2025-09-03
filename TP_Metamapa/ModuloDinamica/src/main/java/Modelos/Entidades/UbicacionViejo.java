package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Ubicacion")
public class UbicacionViejo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUbicacion;
    private String nombre;
    private Double latitud;
    private Double longitud;

    public UbicacionViejo(String nombre, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}