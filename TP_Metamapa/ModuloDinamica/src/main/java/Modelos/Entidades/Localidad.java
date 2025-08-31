package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="Localidad")
public class Localidad {
    String nombre_localidad;
    @ManyToOne
    @JoinColumn(name = "provincia_id_provincia")
    Provincia provincia;
    @Id
    private Long idLocalidad;

    public Localidad() {}

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Localidad(String nombre_localidad, Provincia provincia) {
        this.nombre_localidad = nombre_localidad;
        this.provincia = provincia;
    }

    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Long getIdLocalidad() {
        return idLocalidad;
    }
}
