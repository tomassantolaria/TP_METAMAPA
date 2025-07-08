package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Contenido {
    private String texto;
    private String contenido_multimedia;

    public Contenido(String texto, String contenido_multimedia){
        this.texto = texto;
        this.contenido_multimedia = contenido_multimedia;
    }

}