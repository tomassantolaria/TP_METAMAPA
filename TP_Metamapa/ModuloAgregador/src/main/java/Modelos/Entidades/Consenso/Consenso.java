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

    public Long cantidadFuentesConHecho(Hecho hecho) {
        return repositorio.cantidadDeFuentesConHecho(hecho.getTitulo(),hecho.getCategoria(), hecho.getFecha(), hecho.getUbicacion(), hecho.getContribuyente());
    }

    // CUANDO SE USA EL CONSENSO SE LE DEBE SETAR EL REPOSITORIO
    // VER COMO MEJORAR
}
