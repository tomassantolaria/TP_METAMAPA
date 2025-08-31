package Modelos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FiltrarRequestDTO {
    private CriteriosDTO criterios;
    private List<HechoDTO> hechos;
    public FiltrarRequestDTO(CriteriosDTO criterios, List<HechoDTO> hechos) {
        this.criterios = criterios;
        this.hechos = hechos;
    }
}