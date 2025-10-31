package Modelos;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime ;
import java.util.UUID;

@Getter
@Setter

public class ContribuyenteDTO {
    public String usuario;
    public String nombre;
    public String apellido;
    public LocalDateTime fecha_nacimiento;
    public String keycloakId;
}
