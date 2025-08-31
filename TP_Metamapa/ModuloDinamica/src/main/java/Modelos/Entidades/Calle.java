package Modelos.Entidades;

import Modelos.Entidades.Localidad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name= "Calle")
public class Calle {
    String nombre_calle;
    @ManyToOne
    @JoinColumn(name = "localidad_id_localidad")
    Localidad localidad;
    @Id
    private Long idCalle;

    public Calle(String nombre_calle, Localidad localidad) {
        this.nombre_calle = nombre_calle;
        this.localidad = localidad;
    }

    public Calle() {}

    public void setIdCalle(Long idCalle) {
        this.idCalle = idCalle;
    }

    public Long getIdCalle() {
        return idCalle;
    }
}
