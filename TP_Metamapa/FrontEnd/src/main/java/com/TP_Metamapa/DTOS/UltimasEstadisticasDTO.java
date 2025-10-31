package com.TP_Metamapa.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UltimasEstadisticasDTO{

    private Map<Long, String> provinciaConMasHechosPorColeccion;
    private String categoriaConMasHechos;
    private Map<String, String> provinciaConMasHechosDeCategoria;
    private Map<String, Integer> horaConMasHechosPorCategoria;
    private Long cantidadSolicitudesSpam;


}