package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name= "Provincia")
public class Provincia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvincia;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String nombre_provincia;

    public Provincia() {}
}
