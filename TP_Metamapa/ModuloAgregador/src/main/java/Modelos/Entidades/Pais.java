package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name= "Pais")
public class Pais {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPais;

    String pais;

    public Pais(String nombre_pais) {
        this.pais = nombre_pais;
    }

    public Pais() {}
}
