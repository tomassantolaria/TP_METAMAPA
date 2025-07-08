package Servicios;
import Modelos.DTOs.HechoDTO;
import java.util.List;


public interface IFuenteDemoService {

    List<HechoDTO> obtenerHecho();
    void actualizarHechos();
}