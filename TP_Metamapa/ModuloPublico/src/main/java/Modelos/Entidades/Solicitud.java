package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "Solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolcitud;

    LocalDate fecha_creacion;//date
    @Column(length = 500)
    String motivo;

    @ManyToOne
    @JoinColumn()
    private Hecho hecho;

    @Enumerated(EnumType.STRING)
    @Column()
    Estado estado;

    public Solicitud(Long idSolicitud, LocalDate fecha_creacion, String motivo, Hecho hecho, Estado estado){
        this.idSolcitud = idSolicitud;
        this.fecha_creacion = fecha_creacion;
        this.motivo = motivo;
        this.hecho = hecho;
        this.estado = estado;
    }
    public Solicitud(){}
}