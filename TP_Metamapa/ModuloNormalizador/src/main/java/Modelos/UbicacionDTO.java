package Modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionDTO
{
    String pais;
    String provincia;
    String localidad;

    public UbicacionDTO( String pais, String provincia,String localidad) {
        this.pais = pais;
        this.provincia = provincia;
        this.localidad = localidad;
    }

}
