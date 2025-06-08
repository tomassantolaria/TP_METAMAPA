package Servicios.impl;

import Modelos.Entidades.Hecho;
import Modelos.Repositorios.FuenteDemo_Hechos;
import Servicios.IConexionService;
import Servicios.IFuenteDemoService;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class FuenteDemoService implements IFuenteDemoService {

    private final IConexionService conexionService;
    private final FuenteDemo_Hechos fuenteDemoHechos;

    private LocalDateTime ultimaConsulta;
    private final URL url;

    public FuenteDemoService(FuenteDemo_Hechos fuenteDemoHechos, IConexionService conexionService) {
        this.fuenteDemoHechos = fuenteDemoHechos;
        this.conexionService = conexionService;
        this.ultimaConsulta = null;
        try {
            this.url = new URL("https://fuente-demo.org/api/hechos");
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL inválida", e);
        }
    } // Constructor de la clase

    @Override
    public List<Hecho> obtenerHecho() {
        return fuenteDemoHechos.obtenerHechos();
    } //agregar en controller para que alguien lo use

    @Override
    public void actualizarHechos() {
        List<Hecho> nuevosHechos = new ArrayList<>();
        Map<String, Object> data;

        while((data = conexionService.siguienteHecho(url, ultimaConsulta)) != null){
            nuevosHechos.add(convertirHecho(data));
        }
        fuenteDemoHechos.guardarHechos(nuevosHechos);
        ultimaConsulta = LocalDateTime.now(); // Actualizar la fecha de la última consulta
    }

    private Hecho convertirHecho(Map<String, Object> data) {
        return new Hecho(
                (String) data.get("titulo"),
                (String) data.get("descripcion"),
                (String) data.get("contenido"),
                (String) data.get("contenidoMultimedia"),
                (String) data.get("fecha"),
                (String) data.get("lugar")
        );
    }
}