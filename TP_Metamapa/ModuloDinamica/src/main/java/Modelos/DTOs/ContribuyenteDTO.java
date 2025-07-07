package Modelos.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
public class ContribuyenteDTO {
    private String usuario;
    private String nombre;
    private String apellido;
    private Localdate fecha_nacimiento;
}