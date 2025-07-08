package Servicios.impl;

import Modelos.DTOs.HechoDTO;
import Repositorios.FuenteDemo_Hechos;
import Servicios.IConexionService;
import Servicios.IFuenteDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.LocalDate;
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

    @Autowired
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
    public List<HechoDTO> obtenerHecho() {
        return fuenteDemoHechos.obtenerHechos();
    }

//    @Override
//    public List<HechoDTO> obtenerHecho() {
//        List<HechoDTO> hechos = fuenteDemoHechos.obtenerHechos();
//        return convertirADTO(hechos);
//    }
    @Override
    public void actualizarHechos() {
        List<HechoDTO> nuevosHechos = new ArrayList<>();
        Map<String, Object> data;

        while((data = conexionService.siguienteHecho(url, ultimaConsulta)) != null){
            HechoDTO dto = convertirDTODesdeMap(data);
            nuevosHechos.add(dto);
        }
        fuenteDemoHechos.guardarHechos(url.toString(), nuevosHechos);
        ultimaConsulta = LocalDateTime.now(); // Actualizar la fecha de la última consulta
    }

    private HechoDTO convertirDTODesdeMap(Map<String, Object> data) {
        return new HechoDTO(
                (String) data.get("titulo"),
                (String) data.get("descripcion"),
                (String) data.get("contenido"),
                (String) data.get("contenidoMultimedia"),
                (String) data.get("categoria"),
                (LocalDate) data.get("fecha"), // Si tu JSON devuelve esto como string, hay que parsearlo
                null, // fechaAcontecimiento, si no viene en el JSON
                (String) data.get("lugar"),
                (Double) data.get("latitud"),
                (Double) data.get("longitud"),
                null, null, null, null, null, null, null
        );
    }

//    private Hecho convertirHecho(Map<String, Object> data) {
//        return new Hecho(
//                (String) data.get("titulo"),
//                (String) data.get("descripcion"),
//                (String) data.get("contenido"),
//                (String) data.get("contenidoMultimedia"),
//                (String) data.get("categoria"),
//                (LocalDate) data.get("fecha"),
//                (String) data.get("lugar"),
//                (Double) data.get("latitud"),
//                (Double) data.get("longitud")
//        );
//    }

//    private List<HechoDTO> convertirADTO(List<Hecho> hechos) {
//        List<HechoDTO> hechosDTO = new ArrayList<>();
//        for (Hecho hecho : hechos) {
//            HechoDTO dto = new HechoDTO(
//                    hecho.getTitulo(),
//                    hecho.getDescripcion(),
//                    hecho.getContenido(),
//                    hecho.getContenidoMultimedia(),
//                    hecho.getCategoria(),
//                    hecho.getFecha(),
//                    null,
//                    hecho.getLugar(),
//                    hecho.getLatitud(),
//                    hecho.getLongitud(),
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//            hechosDTO.add(dto);
//        }
//        return hechosDTO;
//    }
}