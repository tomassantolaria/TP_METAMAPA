package Modelos.Entidades.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Getter
@Setter
@Entity
@DiscriminatorValue("ABSOLUTA")
public class ConsensoAbsoluta extends Consenso {


    public ConsensoAbsoluta() {
        super(null); // o inyectar repositorio si usás DI
    }

    public ConsensoAbsoluta(HechoRepositorio repositorio) {
        super(repositorio);
    }

    @Override
    public Boolean tieneConsenso(Hecho hecho) {
        return (cantidadFuentesConHecho(hecho).equals(repositorio.cantidadFuentes()));
    }
}

