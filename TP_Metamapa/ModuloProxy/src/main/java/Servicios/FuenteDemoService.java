package Servicios;

import Modelos.Entidades.FuenteDemo;
import Modelos.Entidades.Hecho;
import java.time.LocalDateTime;
import java.util.HashMap;
import Servicios.Conexion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;


@Service
public class FuenteDemoService implements IFuenteDemoService{

    @Override
    public List<Hecho> obtenerHechos(String url) {
        List<Hecho> hechosObtenidos = new ArrayList<>();
//        LocalDateTime fechaConsulta = (this.fechaUltimaActualizacion != null)
//                ? this.fechaUltimaActualizacion
//                : LocalDateTime.now().minusHours(1);

        Map<String, Object> respuestaConexion = Conexion.siguienteHecho(url, fechaUltimaActualizacion);

        while (respuestaConexion != null) {
            Hecho hechonuevo = extraerHecho(respuestaConexion); // convertís el Map en un objeto Hecho
            hechosObtenidos.add(hechonuevo);
            this.fechaUltimaActualizacion = hechonuevo.getFecha(); // actualizás fecha
            respuestaConexion = Conexion.siguienteHecho(url(urlStr), this.fechaUltimaActualizacion);
        }
        return hechosObtenidos;
//        //mientras en la conexion hayan hechos
//        while (Conexion.siguienteHecho(url, fechaUltimaActualizacion) != null) {
//            Hecho hechonuevo = (Hecho) respuestaConexion.get("Hecho"); //!!!!!!!!!!!!!
//            hechosObtenidos.add(hechonuevo);
//            this.fechaUltimaActualizacion = hecho.getFecha();
//        }
    }

    

    @Override
    public void actualizarHechos() {
        // Creamos una nueva lista para evitar ConcurrentModificationException
        List<String> urls = new ArrayList<>(hechosDiccionario.keySet());
        for (String url : urls) {
            obtenerHechos(url);
        }
    }




}


//
//public class FuenteDemoService implements IFuenteDemoService{
//
//    private Map<String, LocalDateTime> hechosDiccionario = new HashMap<>();
//    private FuenteDemo fuenteDemo;
//
//    public FuenteDemoServiceImpl() {
//        this.fuenteDemo = new FuenteDemo(); // asumimos constructor sin URL fija
//    }
//
//    @Override
//    public List<Hecho> obtenerHechos(String urlStr) {
//        List<Hecho> hechos = new ArrayList<>();
//        LocalDateTime ultimaFecha = hechosDiccionario.getOrDefault(urlStr, LocalDateTime.now().minusHours(1));
//
//        try {
//            URL url = new URL(urlStr);
//            Map<String, Object> datos;
//
//
//            while ((datos = fuenteDemo.obtenerConexion(url, ultimaFecha)) != null) {
//                Hecho hecho = extraerHechoDesdeMap(datos);
//                hechos.add(hecho);
//                ultimaFecha = hecho.getFecha();
//            }
//
//            hechosDiccionario.put(urlStr, ultimaFecha); // actualizamos la última fecha para esa URL
//
//        } catch (Exception e) {
//            System.out.println("Error al obtener hechos: " + e.getMessage());
//        }
//
//        return hechos;
//    }
//
//    @Override
//    public void actualizarHechos() {
//        for (String url : hechosDiccionario.keySet()) {
//            List<Hecho> nuevos = obtenerHechosDesde(url);
//            System.out.println("Se obtuvieron " + nuevos.size() + " hechos de " + url);
//        }
//    }
//
//    private Hecho extraerHechoDesdeMap(Map<String, Object> datos) {
//        // Versión mejorada del método que convierte el Map en un objeto Hecho
//        return new Hecho(
//                (String) datos.get("titulo"),
//                (String) datos.get("descripcion"),
//                (String) datos.get("categoria"),
//                Double.parseDouble(datos.get("latitud").toString()),
//                Double.parseDouble(datos.get("longitud").toString()),
//                java.time.LocalDate.parse((String) datos.get("fecha"))
//        );
//    }


}
