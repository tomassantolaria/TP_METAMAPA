package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "Pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPais;

    String pais;

    public Pais(String nombre_Pais) {
        this.pais = nombre_Pais;
    }

    public Pais() {}
}
