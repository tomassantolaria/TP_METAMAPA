package Controlador.ApisAdministrativas;

import Modelos.DTOs.ColeccionDTO;
import Modelos.DTOs.CriterioDTO;
import Modelos.Entidades.CriteriosDePertenencia;
import Servicio.ColeccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ColeccionControlador {

    @Autowired
    private ColeccionServicio coleccionServicio;

    @PostMapping("/colecciones/crear")
    public ResponseEntity<String> crearColeccion(@RequestBody ColeccionDTO coleccionDTO) {
        try {
            coleccionServicio.crearColeccion(coleccionDTO);
            return ResponseEntity.status(200).body("Coleccion creada exitosamente");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error al crear la coleccion" + e.getMessage());
        }
    }
    @DeleteMapping("/hechos/{id}")
    public ResponseEntity<String> eliminarHecho(@PathVariable UUID id) {
        try {
            coleccionServicio.eliminarHecho(id);
            return ResponseEntity.status(200).body("Hecho eliminado exitosamente");
        } catch (Exception e){
            return ResponseEntity.status(500).body("Error al eliminar el hecho" + e.getMessage());
        }
    }

    @PutMapping ("/colecciones/{id}/modificarAlgoritmoConsenso/{estrategia}")
    public ResponseEntity<String> modificarAlgoritmoConsenso(@PathVariable UUID id, @PathVariable String estrategia) {
        try{
            coleccionServicio.modificarConsenso(id, estrategia);
            return ResponseEntity.status(200).body("Consenso modificado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al modificar el consenso: " + e.getMessage());
        }
    }

    @PostMapping ("/colecciones/{id}/agregarFuente/{fuente}")
    public ResponseEntity<String> agregarFuente(@PathVariable UUID id, @PathVariable UUID fuente) {
        try{
            coleccionServicio.agregarFuente(id, fuente);
            return ResponseEntity.status(200).body("Hechos de la fuente " + fuente + " agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al agregar hechos de la fuente " + fuente + ":" + e.getMessage());
        }
    }

    @DeleteMapping ("/colecciones/{id}/eliminarFuente/{fuente}")
    public ResponseEntity<String> eliminarFuente(@PathVariable UUID id, @PathVariable UUID fuente) {
        try{
            coleccionServicio.eliminarFuente(id, fuente);
            return ResponseEntity.status(200).body("Hechos de la fuente " + fuente + " eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar hechos de la fuente " + fuente + ":" + e.getMessage());
        }
    }

    @DeleteMapping ("/colecciones/{id}/elimarColeccion")
    public ResponseEntity<String> eliminarColeccion (@PathVariable UUID id) {
        try{
            coleccionServicio.eliminarColeccion(id);
            return ResponseEntity.status(200).body("Coleccion eliminada " +  id + " exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la coleccion " + id + ":" + e.getMessage());
        }
    }

    ///  modificar criterio de pertenencia


}
