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
@DiscriminatorValue("MAYORIA_SIMPLE")
public class ConsensoMayoriaSimple extends Consenso {


    public ConsensoMayoriaSimple() {
        super(null); // o inyectar repositorio si usás DI
    }

    public ConsensoMayoriaSimple(HechoRepositorio repositorio) {
        super(repositorio);
    }


    public Boolean tieneConsenso(Hecho hecho) {

        Long cantidadFuentes = repositorio.cantidadFuentes();
        return (cantidadFuentesConHecho(hecho)) >= cantidadFuentes / 2;
    }



}
