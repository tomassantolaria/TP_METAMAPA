package Modelos.Entidades;

import Modelos.Entidades.Localidad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name= "Calle")
public class Calle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalle;
    String nombre_calle;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    Localidad localidad;


    public Calle(String nombre_calle, Localidad localidad) {
        this.nombre_calle = nombre_calle;
        this.localidad = localidad;
    }

    public Calle() {}


}
