package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Etiqueta {
    String etiqueta;

    public Etiqueta(String etiqueta){
        this.etiqueta = etiqueta;
    }
}
