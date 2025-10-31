package Repositorio;

import Modelos.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContenidoRepositorio extends JpaRepository<Contenido, Long> {

    List<Contenido> findByTextoAndContenidoMultimedia(String texto, String contenidoMultimedia);
}

