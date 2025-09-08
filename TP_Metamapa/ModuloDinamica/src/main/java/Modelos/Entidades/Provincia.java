package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvincia;
    String nombre_provincia;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    Pais pais;


    public Provincia() {
    }


    public Provincia(String nombre_provincia, Pais pais) {
        this.nombre_provincia = nombre_provincia;
        this.pais = pais;
    }
}
