package Servicios;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;


public interface IConexionService {
    Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta);
}
