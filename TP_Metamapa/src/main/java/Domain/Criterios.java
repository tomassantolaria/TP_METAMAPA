package Domain;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Criterios {
    String titulo;
    String descripcion;
    Contenido contenido;
    Categoria categoria;
    LocalDate fecha;
    Ubicacion ubicacion;
    LocalDate fecha_carga;
    OrigenCarga origen_carga;
}
