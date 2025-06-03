package Servicios;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;


public interface Iconexion {
    Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta);
}
