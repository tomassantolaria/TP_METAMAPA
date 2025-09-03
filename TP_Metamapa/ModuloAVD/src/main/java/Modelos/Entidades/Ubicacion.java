package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUbicacion;
    @ManyToOne
    @JoinColumn()
    Calle calle;
    @ManyToOne
    @JoinColumn()
    Localidad localidad;
    @ManyToOne
    @JoinColumn()
    Provincia provincia;

    Double latitud;
    Double longitud;

    public Ubicacion(Calle calle, Localidad localidad, Provincia provincia, Double latitud, Double longitud) {
        this.calle = calle;
        this.localidad = localidad;
        this.provincia = provincia;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Ubicacion() {}
}
