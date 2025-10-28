package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class HechoDTOInput {
    // Campos que coinciden con el backend
    private String titulo;
    private String descripcion;
    private String contenido; // Asegúrate si es 'contenido' o 'contenidoAdicional' en el backend
    private String contenido_multimedia; // ¡Será la RUTA (String)!
    private String categoria;
    private LocalDateTime fechaAcontecimiento; // ¡LocalDateTime!
    private String localidad;
    private String provincia;
    private String pais;
    private Double latitud;
    private Double longitud;
    private String usuario; // Username
    private String nombre; // Nombre del usuario
    private String apellido; // Apellido del usuario
    private LocalDateTime fecha_nacimiento; // Fecha nacimiento del usuario
    private Boolean anonimo;

    // Puedes necesitar un constructor o dejar que Jackson/RestTemplate lo manejen
    public HechoDTOInput() {}
}
