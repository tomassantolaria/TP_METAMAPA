package Modelos.Entidades;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Archivo {
    private Long id;
    private String path;

    //TODO : ID AUTOINCREMENTAL
    public Archivo( String path) {
        this.path = path;
    }

}
