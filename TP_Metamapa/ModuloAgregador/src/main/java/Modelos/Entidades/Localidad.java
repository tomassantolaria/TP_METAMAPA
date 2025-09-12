package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "Localidad")
public class Localidad {
    String nombre_localidad;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    Provincia provincia;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalidad;

    public Localidad(String nombre_Localidad, Provincia provincia) {
        this.localidad = nombre_Localidad;
        this.provincia = provincia;
    }

    public Localidad() {}

}
