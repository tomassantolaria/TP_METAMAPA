package Modelos.Entidades.Consenso;
import Modelos.Entidades.Consenso.Consenso;
import Modelos.Entidades.Hecho;
import Repositorio.HechoRepositorio;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("ABSOLUTA")
public class ConsensoAbsoluta extends Consenso {


}

