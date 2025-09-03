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
@DiscriminatorValue("MULTIPLES_MENCIONES")
public class ConsensoMultiplesMenciones extends Consenso{

    public ConsensoMultiplesMenciones() {
        super(null); // o inyectar repositorio si usás DI
    }

    public ConsensoMultiplesMenciones(HechoRepositorio repositorio) {
        super(repositorio);
    }


    public Boolean tieneConsenso(Hecho hecho) {
        Boolean noHayConIgualTituloYDiferentesAtributos = repositorio.cantidadDeFuentesConMismoTituloDiferentesAtributos(hecho.getTitulo(), hecho.getDescripcion(), hecho.getCategoria(), hecho.getFecha(), hecho.getUbicacion(), hecho.getContribuyente(), hecho.getContenido()) == 0 ;

        return (cantidadFuentesConHecho(hecho) >= 2) && (noHayConIgualTituloYDiferentesAtributos);
    }

}
