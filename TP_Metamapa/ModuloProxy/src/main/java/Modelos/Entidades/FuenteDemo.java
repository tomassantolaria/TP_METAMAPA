package Modelos.Entidades;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FuenteDemo
{
    private static FuenteDemo instancia;
    private List<Hecho> hechoslista;
    private LocalDateTime fechaUltimaActualizacion;

    private FuenteDemo() {}

    public static synchronized FuenteDemo getInstancia() {
        if (instancia == null) {
            instancia = new FuenteDemo();
        }
        return instancia;

    }
}
