package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name= "Provincia")
public class Provincia {
    @Id
    private Long idProvincia;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String nombre_provincia;

    public Provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public Provincia() {}
}
