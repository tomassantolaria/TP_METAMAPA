package Servicio;


import Modelos.Entidades.*;
import Modelos.Entidades.Consenso.Consenso;
import Repositorio.ColeccionRepositorio;
import Repositorio.HechoRepositorio;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColeccionServicio {


    ColeccionRepositorio coleccionRepositorio;
    @Autowired
    HechoRepositorio hechoRepositorio;


    public ColeccionServicio(ColeccionRepositorio coleccionRepositorio) {
        this.coleccionRepositorio = coleccionRepositorio;
    }

    @Transactional
    public void actualizarHechosConsensuados() {
       for (Coleccion coleccion : coleccionRepositorio.findAll()) {
           Hibernate.initialize(coleccion.getHechos());
           Consenso consenso = coleccion.getConsenso();
           List<Hecho> hechos;
           if (consenso != null) {
               hechos = coleccion.getHechos().stream()
                       .filter(h -> consenso.tieneConsenso(h, hechoRepositorio))
                       .collect(Collectors.toCollection(ArrayList::new));
           } else {
               hechos = new ArrayList<>(coleccion.getHechos());
           }
            coleccion.setHechosConsensuados(hechos);
            coleccionRepositorio.save(coleccion);

       }
    }

}

