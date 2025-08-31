package Modelos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FiltrarRequestDTO {
    private CriteriosDTO criterios;
    private List<HechoDTOInput> hechos;
    public FiltrarRequestDTO(CriteriosDTO criterios, List<HechoDTOInput> hechos) {
        this.criterios = criterios;
        this.hechos = hechos;
    }
}
