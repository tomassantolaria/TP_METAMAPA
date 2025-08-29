package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table (name = "Solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolcitud;

    private LocalDate fecha_creacion; //date
    private String motivo;
    @ManyToOne
    @JoinColumn(name = "id_hecho")
    private Hecho hecho; //Supongo que lo mejor sería que la solicitud ingrese el id del hecho y no el hecho entero. Consultar
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    public Solicitud(Long idSolicitud, LocalDate fecha_creacion, String motivo, Hecho hecho, Estado estado){
        this.idSolcitud = idSolicitud;
        this.fecha_creacion = fecha_creacion;
        this.motivo = motivo;
        this.hecho = hecho;
        this.estado = estado;
    }
    public Solicitud(){}
}