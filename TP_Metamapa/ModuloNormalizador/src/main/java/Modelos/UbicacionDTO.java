package Modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionDTO
{
    String localidad;
    String provincia;
    String pais;

    public UbicacionDTO(){}

    public UbicacionDTO( String localidad, String provincia, String pais) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
    }

}
