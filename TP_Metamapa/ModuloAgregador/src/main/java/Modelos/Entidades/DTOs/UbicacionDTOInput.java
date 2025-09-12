package Modelos.Entidades.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionDTOInput {

    private String pais;
    private String provincia;
    private String localidad;
    private Double latitud;
    private Double longitud;


    public UbicacionDTOInput(String pais, String provicia, String localidad, Double latitud, Double longitud) {
        this.pais = pais;
        this.provincia = provicia;
        this.localidad = localidad;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
