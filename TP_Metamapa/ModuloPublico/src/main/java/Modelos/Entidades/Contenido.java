package Modelos.Entidades;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Contenido")
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private String contenidoMultimedia;

    public Contenido(String texto, String contenido_multimedia){
        this.texto = texto;
        this.contenidoMultimedia = contenido_multimedia;
    }

    public Contenido(){}

}
