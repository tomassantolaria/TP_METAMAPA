package Modelos.Entidades.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UbicacionDTOOutput {
    private Double latitud;
    private Double longitud;


    public UbicacionDTOOutput(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
