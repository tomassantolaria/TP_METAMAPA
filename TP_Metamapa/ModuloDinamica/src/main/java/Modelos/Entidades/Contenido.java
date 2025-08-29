package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Contenido")
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContenido;
    private String texto;
    private String contenido_multimedia;

    public Contenido(String texto, String contenido_multimedia){
        this.texto = texto;
        this.contenido_multimedia = contenido_multimedia;
    }

}