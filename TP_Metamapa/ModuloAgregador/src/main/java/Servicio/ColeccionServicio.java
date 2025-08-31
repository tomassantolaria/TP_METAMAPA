package Servicio;


import Modelos.Entidades.*;
import Repositorio.ColeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColeccionServicio {


    @Autowired
    ColeccionRepositorio coleccionRepositorio;


    public ColeccionServicio(ColeccionRepositorio coleccionRepositorio) {
        this.coleccionRepositorio = coleccionRepositorio;
    }


    public void actalizarHechosConsensuados() {
       for (Coleccion coleccion : coleccionRepositorio.findAll()) {
            List<Hecho> hechos = actualizarHechosConsensuados(coleccion);
            coleccion.setHechosConsensuados(hechos);
            coleccionRepositorio.save(coleccion);
       }
    }

    public List<Hecho> actualizarHechosConsensuados(Coleccion coleccion) {
        if (coleccion.getConsenso() != null ) {
            return coleccion.getHechos().stream().filter(hecho -> coleccion.getConsenso().tieneConsenso(hecho)).toList();
        }
            return coleccion.getHechos();
    }
}

