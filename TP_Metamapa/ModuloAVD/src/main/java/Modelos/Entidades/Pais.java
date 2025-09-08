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

    String nombre_Pais;

    public Pais(String nombre_Pais) {
        this.nombre_Pais = nombre_Pais;
    }

    public Pais() {}
}
