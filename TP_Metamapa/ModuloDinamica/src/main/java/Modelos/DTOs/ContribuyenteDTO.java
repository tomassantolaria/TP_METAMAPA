package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContribuyenteDTO {
    private String usuario;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
}