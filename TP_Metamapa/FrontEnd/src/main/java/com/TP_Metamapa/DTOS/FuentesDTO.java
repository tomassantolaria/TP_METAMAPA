package com.TP_Metamapa.DTOS;

import com.TP_Metamapa.Modelos.TipoFuente;
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
