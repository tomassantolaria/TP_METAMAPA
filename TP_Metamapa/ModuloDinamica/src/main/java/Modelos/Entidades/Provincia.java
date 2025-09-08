package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name= "Provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvincia;
    String nombre_provincia;

    public Provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public Provincia() {}
}
