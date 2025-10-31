package Modelos.DTOs;

import Modelos.Entidades.TipoFuente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuentesDTO {
    String url;
    TipoFuente tipoFuente;
}
