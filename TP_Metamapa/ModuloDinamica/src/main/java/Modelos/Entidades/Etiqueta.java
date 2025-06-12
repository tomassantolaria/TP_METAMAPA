package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Etiqueta {
    public String etiqueta;

    public Etiqueta(String etiqueta){
        this.etiqueta = etiqueta;
    }
}