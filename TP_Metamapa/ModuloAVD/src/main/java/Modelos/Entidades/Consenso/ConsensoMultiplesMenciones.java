package Modelos.Entidades.Consenso;

import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("MULTIPLES_MENCIONES")
public class ConsensoMultiplesMenciones extends Consenso{


}
