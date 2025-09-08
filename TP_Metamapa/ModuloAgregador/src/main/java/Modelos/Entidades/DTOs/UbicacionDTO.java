package Modelos.Entidades.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UbicacionDTO{
    private String pais;
    private String provincia;
    private String localidad;

    public UbicacionDTO(String pais, String provincia, String localidad) {
        this.pais = pais;
        this.provincia = provincia;
        this.localidad = localidad;
    }
}
