package Modelos;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter

public class ContribuyenteDTO {
    public String usuario;
    public String nombre;
    public String apellido;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate fecha_nacimiento;
}
