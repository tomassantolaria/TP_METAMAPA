package Servicios;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

public interface IConexionService {
    List<Map<String, Object>> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta);
}