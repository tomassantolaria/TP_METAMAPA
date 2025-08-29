package Modelos.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ContribuyenteDTO {
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDate fecha_nacimiento;
}
