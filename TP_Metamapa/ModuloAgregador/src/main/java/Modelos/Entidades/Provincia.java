package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="Provincia")
public class Provincia {
    String nombre_provincia;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    Pais pais;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvincia;

    public Provincia() {}



    public Provincia(String nombre_provincia, Pais pais) {
        this.provincia = nombre_provincia;
        this.pais = pais;
    }

}
