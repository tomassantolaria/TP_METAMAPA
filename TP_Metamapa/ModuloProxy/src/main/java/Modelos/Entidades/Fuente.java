package Modelos.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Modelos.Entidades.*;
import java.time.LocalDateTime ;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Fuente")
public class Fuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private LocalDateTime  fechaUltimaConsulta;
    @Enumerated(EnumType.STRING)
    @Column()
    private TipoFuente tipoFuente;

    public Fuente(String url, LocalDateTime fechaUltimaConsulta, TipoFuente tipoFuente){
        this.url = url;
        this.fechaUltimaConsulta = fechaUltimaConsulta;
        this.tipoFuente = tipoFuente;
    }
}