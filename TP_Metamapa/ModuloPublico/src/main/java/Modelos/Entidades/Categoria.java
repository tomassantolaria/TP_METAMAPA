package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Categoria(String nombre){
        this.nombre = nombre;
    }

    public Categoria(){}

}

