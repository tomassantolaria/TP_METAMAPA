package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime ;


@Getter
@Setter
@Entity
@Table (name = "Solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolcitud;

    private LocalDateTime fecha_creacion; //date
    @Column(length = 500)
    private String motivo;
    @ManyToOne
    @JoinColumn()
    private Hecho hecho;
    @Enumerated(EnumType.STRING)
    @Column()
    private Estado estado;

    public Solicitud(Long idSolicitud, LocalDateTime fecha_creacion, String motivo, Hecho hecho, Estado estado){
        this.idSolcitud = idSolicitud;
        this.fecha_creacion = fecha_creacion;
        this.motivo = motivo;
        this.hecho = hecho;
        this.estado = estado;
    }
    public Solicitud(){}
}