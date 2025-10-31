package Modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "Localidad")
public class Localidad {
    String localidad;
    @ManyToOne()
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
