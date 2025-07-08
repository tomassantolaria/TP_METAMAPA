package Servicios;


import Modelos.DTOs.SolicitudDTO;
import Modelos.DTOs.HechoDTO;

import java.util.List;

public interface IFuenteMetaMapaService {

     List<HechoDTO> obtenerHechos(String categoria,
                                        String fecha_reporte_desde,
                                        String fecha_reporte_hasta,
                                        String fecha_acontecimiento_desde,
                                        String fecha_acontecimiento_hasta,
                                        String ubicacion);
    List<HechoDTO> obtenerHechosPorColeccion(String idColeccion,
                                                    String categoria,
                                                    String fecha_reporte_desde,
                                                    String fecha_reporte_hasta,
                                                    String fecha_acontecimiento_desde,
                                                    String fecha_acontecimiento_hasta,
                                                    String ubicacion);
    void crearSolicitud(SolicitudDTO solicitud) throws Exception;
}
