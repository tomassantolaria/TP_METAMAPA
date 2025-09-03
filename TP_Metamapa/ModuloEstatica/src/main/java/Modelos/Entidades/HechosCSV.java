package Modelos.Entidades;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HechosCSV {
    private List<HechoCSV> hechos;

//    public HechosCSV(List<HechoCSV> hechos) {
//        this.hechos = hechos;
//    }

    public void addHecho (HechoCSV hecho) {
        if (! hechos.contains(hecho)) {
            hechos.add(hecho);
        }
    }
}
