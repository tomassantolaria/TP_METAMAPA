package Servicios.impl;

import Servicios.IConexionService;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConexionService implements IConexionService {


    @Override
    public Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta) {
        Map<String, Object> hechos = new HashMap<>();
        // Implementar la lógica para obtener el siguiente hecho desde la URL
        return hechos;
    }
}