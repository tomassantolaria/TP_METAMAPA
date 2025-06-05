package Modelos.Entidades;



import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FuenteProxy
{
    private static FuenteProxy instancia;
    private LocalDateTime fechaUltimaActualizacion;
    private List<String> urls;

    private FuenteProxy() {}

    public static synchronized FuenteProxy getInstancia() {
        if (instancia == null) {
            instancia = new FuenteProxy();
        }
        return instancia;

    }
}
