package Controlador;

import Modelos.DTOs.ColeccionDTO;
import Servicio.ColeccionServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ColeccionControlador {
    private final ColeccionServicio coleccionServicio;

    public ColeccionControlador(ColeccionServicio coleccionServicio) {
        this.coleccionServicio = coleccionServicio;
    }

    @PostMapping("/coleccion")
    public ResponseEntity<String> crearColeccion(@RequestBody ColeccionDTO coleccionDTO) {
        try {
            coleccionServicio.crearColeccion(coleccionDTO);
            return ResponseEntity.status(200).body("Coleccion creada exitosamente");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error al crear la coleccion" + e.getMessage());
        }
    }
    @DeleteMapping("/hechos/{id}")
    public ResponseEntity<String> eliminarHecho(@PathVariable Long id) {
        try {
            coleccionServicio.eliminarHecho(id);
            return ResponseEntity.status(200).body("Hecho eliminado exitosamente");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error al eliminar el hecho" + e.getMessage());
        }
    }

    @PutMapping ("/coleccion/{id}/Consenso/{estrategia}")
    public ResponseEntity<String> modificarAlgoritmoConsenso(@PathVariable Long id, @PathVariable String estrategia) {
        try{
            coleccionServicio.modificarConsenso(id, estrategia);
            return ResponseEntity.status(200).body("Consenso modificado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al modificar el consenso: " + e.getMessage());
        }
    }

    @PostMapping ("/coleccion/{id}/{fuente}")
    public ResponseEntity<String> agregarFuente(@PathVariable Long id, @PathVariable Long fuente) {
        try{
            coleccionServicio.agregarFuente(id, fuente);
            return ResponseEntity.status(200).body("Hechos de la fuente " + fuente + " agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al agregar hechos de la fuente " + fuente + ":" + e.getMessage());
        }
    }

    @DeleteMapping ("/coleccion/{id}/{fuente}")
    public ResponseEntity<String> eliminarFuente(@PathVariable Long id, @PathVariable Long fuente) {
        try{
            coleccionServicio.eliminarFuente(id, fuente);
            return ResponseEntity.status(200).body("Hechos de la fuente " + fuente + " eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar hechos de la fuente " + fuente + ":" + e.getMessage());
        }
    }

    @DeleteMapping ("/colecciones/{id}")
    public ResponseEntity<String> eliminarColeccion (@PathVariable Long id) {
        try{
            coleccionServicio.eliminarColeccion(id);
            return ResponseEntity.status(200).body("Coleccion eliminada " +  id + " exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la coleccion " + id + ":" + e.getMessage());
        }
    }

    ///  modificar criterio de pertenencia


}
