package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="Localidad")
public class Localidad {
    String nombre_localidad;
    @ManyToOne
    @JoinColumn()
    Provincia provincia;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalidad;

    public Localidad() {}

    public Localidad(String nombre_localidad, Provincia provincia) {
        this.nombre_localidad = nombre_localidad;
        this.provincia = provincia;
    }


}
