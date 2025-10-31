package servicios;

import Modelos.DTOs.FuentesDTO;
import Modelos.Entidades.Fuente;
import Repositorios.FuenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuentesServicio {

    @Autowired
    FuenteRepositorio fuenteRepositorio;

    public List<FuentesDTO> obtenerFuentes(){
        return this.convertirADTO(fuenteRepositorio.findAll());
    }

    private List<FuentesDTO> convertirADTO(List<Fuente> fuentes){
        return fuentes.stream().map(fuente -> new FuentesDTO(
                fuente.getUrl(),
                fuente.getTipoFuente()
        )).toList();
    }

    public void crearFuente(FuentesDTO fuenteDTO){
        Fuente fuente = new Fuente(fuenteDTO.getUrl(),null, fuenteDTO.getTipoFuente());
        fuenteRepositorio.save(fuente);
    }
}
