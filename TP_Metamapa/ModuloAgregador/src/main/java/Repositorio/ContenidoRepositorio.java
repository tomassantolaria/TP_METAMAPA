package Repositorio;

import Modelos.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContenidoRepositorio extends JpaRepository<Contenido, Long> {

    Contenido findByTextoAndContenidoMultimedia(String texto, String contenidoMultimedia);
}

