package Servicio;


import Modelos.Entidades.*;
import Modelos.Entidades.Consenso.Consenso;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColeccionServicio {


    @Autowired
    ColeccionRepositorio coleccionRepositorio;
    HechoRepositorio hechoRepositorio;


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
            Consenso consenso = coleccion.getConsenso();
            consenso.setRepositorio(hechoRepositorio); // PORQUE SE INSATNCIA CON REPO NULL
            return coleccion.getHechos().stream().filter(hecho -> consenso.tieneConsenso(hecho)).toList();
        }
            return coleccion.getHechos();
    }
}

