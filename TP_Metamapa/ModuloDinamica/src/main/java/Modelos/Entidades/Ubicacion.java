package Modelos.Entidades;

import lombok.Getter;

@Getter
public class Ubicacion {
    private String nombre;
    private Double latitud;
    private Double longitud;

    public Ubicacion(String nombre, Double latitud, Double longitud) {}
}