package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="Provincia")
public class Provincia {
    String provincia;
    @ManyToOne
    @JoinColumn()
    Pais pais;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvincia;

    public Provincia() {}

    public Provincia(String provincia, Pais pais) {
        this.provincia = provincia;
        this.pais = pais;
    }


}
