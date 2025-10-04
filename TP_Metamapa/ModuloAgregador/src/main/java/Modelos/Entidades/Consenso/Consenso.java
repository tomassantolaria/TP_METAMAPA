package Modelos.Entidades.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_consenso")
public abstract class Consenso {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    protected HechoRepositorio repositorio;


    protected Consenso() {
    }

    public Consenso(HechoRepositorio repositorio) {
        this.repositorio = repositorio;
    }


    public abstract Boolean tieneConsenso(Hecho hecho);
    public Boolean tieneConsenso(Hecho hecho, HechoRepositorio repo) {
        if (repo == null) {
            throw new IllegalArgumentException("HechoRepositorio no puede ser null");
        }
        HechoRepositorio previous = this.repositorio;
        this.repositorio = repo;
        try {
            return this.tieneConsenso(hecho);
        } finally {
            // Restaurar por seguridad
            this.repositorio = previous;
        }

    }

    public Long cantidadFuentesConHecho(Hecho hecho) {
        return repositorio.cantidadDeFuentesConHecho(hecho.getTitulo(),hecho.getCategoria(), hecho.getFecha(), hecho.getUbicacion());
    }

    // CUANDO SE USA EL CONSENSO SE LE DEBE SETAR EL REPOSITORIO
    // VER COMO MEJORAR
}
