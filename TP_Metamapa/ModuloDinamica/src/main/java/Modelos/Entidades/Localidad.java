package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Localidad")
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalidad;
    String nombre_localidad;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    Provincia provincia;


    public Localidad() {
    }


    public Localidad(String nombre_localidad, Provincia provincia) {
        this.nombre_localidad = nombre_localidad;
        this.provincia = provincia;
    }
}
