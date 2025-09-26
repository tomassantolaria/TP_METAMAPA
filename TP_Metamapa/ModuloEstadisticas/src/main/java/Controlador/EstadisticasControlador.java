package Controlador;

import Servicio.EstadisticasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*; 
import Servicio.Modelos.Exceptions.ColeccionNoEncontradaException;
import Servicio.Modelos.Exceptions.CategoriaNoEncontradaException;


@RestController
@RequestMapping("/estadisticas")
public class EstadisticasControlador{

    private final EstadisticasServicio estadisticasServicio;

    public EstadisticasControlador(EstadisticasServicio estadisticasServicio) {
        this.estadisticasServicio = estadisticasServicio;
    }

    @GetMapping("/coleccion/{idColeccion}/provincia-max-hechos")
    public ResponseEntity<String> obtenerProvinciaConMasHechos(@PathVariable Long idColeccion) {
        try{
            String provincia = estadisticasServico.provinciaConMasHechos(idColeccion);
            return ResponseEntity.status(200).body(provincia);
        }
        catch(ColeccionNoEncontradaException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/coleccion/categoria-max-hechos")
    public ResponseEntity<String> obtenerCategoriaConMasHechos(){
        try{
            String categoria = estadisticasServicio.categoriaConMasHechos();
            return ResponseEntity.status(200).body(categoria);
        }catch (ColeccionNoEncontradaException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/hechos/{categoria}/provincia-max-hechos-categoria")
    public ResponseEntity<String> obtenerProvinciaConMasHechosDeCategoria(@PathVariable String categoria){
        try{
            String provincia = estadisticasServicio.provinciaConMasHechosDeCategoria(categoria);
            return ResponseEntity.status(200).body(provincia);
        } catch(CategoriaNoEncontradaException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
    @GetMapping("/categoria/{categoria}/hora-max-hechos")
    public ResponseEntity<Integer> obtenerHoraConMasHechos(@PathVariable String categoria){
        try{ 
            Integer hora = estadisticasServicio.obtenerHoraConMasHechos(categoria);
            return ResponseEntity.status(200).body(hora);
        }catch (CategoriaNoEncontradaException e){
            return ResponseEntity.status(404).body("Categoría no encontrada");
        }
    }

    @GetMapping("/solicitudes/spam/total")
    public ResponseEntity<Long> obtenerCantidadSolicitudesSpam(){
        Long cantidad = estadisticasServicio.cantidadSolicitudesSpam();
        return ResponseEntity.status(200).body(cantidad);
    }  
    
}
