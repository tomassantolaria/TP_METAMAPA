package Controller;

import Modelos.FiltrarRequestDTO;
import Servicios.FiltradorServicio;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import Modelos.HechoDTO;

import java.util.List;

@RestController
public class FiltradorController
{
    @Autowired
    FiltradorServicio filtradorServicio;

    @PostMapping("/filtrar")
    public ResponseEntity<List<HechoDTO>> filtrarHechos(@RequestBody FiltrarRequestDTO request) {
        List<HechoDTO> hechosFiltrados = filtradorServicio.filtrarHechos(request.getHechos(), request.getCriterios());
        return new ResponseEntity<>(hechosFiltrados, HttpStatus.OK);
    }
}
