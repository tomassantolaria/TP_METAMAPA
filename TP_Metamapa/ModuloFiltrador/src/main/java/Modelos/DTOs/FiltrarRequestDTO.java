package Modelos.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class FiltrarRequestDTO {
    private CriteriosDTO criterios;
    private List<HechoDTO> hechos;

    // getters y setters
}